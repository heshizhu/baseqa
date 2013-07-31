package edu.cmu.lti.oaqa.core.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.analysis_component.JCasMultiplier_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.SetMultimap;

/**
 * This class acts as a wrapper for a particular view in a {@link JCas} or a middle layer between
 * the view in {@link JCas} and the wrappers. The constructor {@link #getWrapperIndexer(JCas)} takes
 * a view as the input.
 * <p>
 * To use it as a wrapper for {@link JCas}, one can retrieve all the wrappers by the {@link Class}
 * of the wrapper, similar to {@link JCas#getAnnotationIndex(int)}, by invoking
 * {@link #getWrappersByTypes(List)}.
 * <p>
 * Since this class is responsible to guarantee the one-to-one correspondence between {@link TOP}s
 * in the view of the {@link JCas} and the wrappers included in an instance of this class. Two
 * mappings have been creat: a mapping between the native hash code (aka identical Java Object
 * reference id for each instance) of wrappers to the actual TOPs, and a mapping between the
 * identical address of TOPs to the actual wrappers. Identical hash code (from
 * {@link System#identityHashCode(Object)} should be used since most wrappers override original
 * {@link Object#hashCode()} method, whereas the identical address is generated from
 * {@link TOP#getAddress()}. Both mappings ensure the uniqueness of wrapper while wrappingand the
 * uniqueness of TOP while unwrapping. {@link #checkWrapped(TOP)} and {@link #getWrapped(TOP)} can
 * be used to check and retrieve the wrapper by a {@link TOP}, {@link #checkUnwrapped(TopWrapper)}
 * and {@link #getUnwrapped(TopWrapper)} can be used to check and retrieve the TOP by a
 * {@link TopWrapper}.
 * <p>
 * Update: Since it is not a fully CAS-equivalent indexer, CAS passed into the method
 * {@link JCasMultiplier_ImplBase#next()} as the only argument will share the same hash code, and
 * once a CAS is release, the corresponding wrapper will not be released. Therefore, it is unsafe to
 * use a global mapping between CASes and indexers. One should directly call the constructor
 * {@link #WrapperIndexer()} to create a new instance.
 * 
 * @author Zi Yang <ziy@cs.cmu.edu>
 * 
 */
public class WrapperIndexer {

  /*
   * Global static variables and methods
   */
  /**
   * A global mapping variable to simulate the whole CAS including multiple views.
   * <p>
   * TODO A wrapper for the global JCas can be implemented, which needs to be aligned with CAS
   * (release, multiply, etc.)
   * 
   * @deprecated Since it is not a fully CAS-equivalent indexer, CAS passed into the method
   *             {@link JCasMultiplier_ImplBase#next()} as the only argument will share the same
   *             hash code, and once a CAS is release, the corresponding wrapper will not be
   *             released. Therefore, it is unsafe to use a global mapping between CASes and
   *             indexers.
   */
  @Deprecated
  private static Map<Integer, WrapperIndexer> jcasHash2wrapperIndexer = Maps.newHashMap();

  @Deprecated
  private static void addJCasWrapperIndexerPair(JCas jcas, WrapperIndexer indexer) {
    jcasHash2wrapperIndexer.put(System.identityHashCode(jcas), indexer);
  }

  /**
   * 
   * @param jcas
   * @return
   * 
   * @deprecated
   * @see #WrapperIndexer()
   * 
   */
  @Deprecated
  public static WrapperIndexer getWrapperIndexer(JCas jcas) {
    int jcasHash = System.identityHashCode(jcas);
    return jcasHash2wrapperIndexer.containsKey(jcasHash) ? jcasHash2wrapperIndexer.get(jcasHash)
            : new WrapperIndexer(jcas);
  }

  /*
   * Member variables and methods
   */
  @Deprecated
  private JCas jcas;

  /**
   * A local cache for the mapping between wrapper {@link Class} and actual wrappers.
   */
  private SetMultimap<Integer, TopWrapper<? extends TOP>> type2wrappers;

  /**
   * A mapping between the native hash code (aka identical Java Object reference id for each
   * instance) of wrappers to the actual TOPs. Identical hash code (from
   * {@link System#identityHashCode(Object)} should be used since most wrappers override original
   * {@link Object#hashCode()} method. The mapping ensures the uniqueness of top while unwrapping.
   */
  private Map<Integer, TOP> wrapperHashcode2top;

  /**
   * A mapping between the identical address of TOPs to the actual wrappers. Identical address is
   * generated from {@link TOP#getAddress()}. The mapping ensures the uniqueness of wrapper while
   * wrapping.
   */
  private Map<Integer, TopWrapper<? extends TOP>> topAddress2wrapper;

  public WrapperIndexer() {
    type2wrappers = HashMultimap.create();
    wrapperHashcode2top = Maps.newHashMap();
    topAddress2wrapper = Maps.newHashMap();
  }

  /**
   * @param jcas
   * 
   * @deprecated
   * @see #WrapperIndexer()
   */
  @Deprecated
  private WrapperIndexer(JCas jcas) {
    this.jcas = jcas;
    type2wrappers = HashMultimap.create();
    wrapperHashcode2top = Maps.newHashMap();
    topAddress2wrapper = Maps.newHashMap();
    addJCasWrapperIndexerPair(jcas, this);
  }

  public Set<TopWrapper<? extends TOP>> getWrappersByType(JCas jcas, int type)
          throws AnalysisEngineProcessException, IllegalArgumentException, SecurityException,
          InstantiationException, IllegalAccessException, NoSuchFieldException,
          ClassNotFoundException, CASException {
    if (!type2wrappers.containsKey(type)) {
      addClassWrappersToIndex(jcas, type);
    }
    return type2wrappers.get(type);
  }

  /**
   * 
   * @param type
   * @return
   * @throws AnalysisEngineProcessException
   * @throws IllegalArgumentException
   * @throws SecurityException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws NoSuchFieldException
   * @throws ClassNotFoundException
   * @throws CASException
   * 
   * @deprecated
   * @see #getWrappersByType(JCas, int)
   */
  @Deprecated
  public Set<TopWrapper<? extends TOP>> getWrappersByType(int type)
          throws AnalysisEngineProcessException, IllegalArgumentException, SecurityException,
          InstantiationException, IllegalAccessException, NoSuchFieldException,
          ClassNotFoundException, CASException {
    if (!type2wrappers.containsKey(type)) {
      addClassWrappersToIndex(type);
    }
    return type2wrappers.get(type);
  }

  public List<Set<TopWrapper<? extends TOP>>> getWrappersByTypes(JCas jcas, List<Integer> types)
          throws AnalysisEngineProcessException, IllegalArgumentException, SecurityException,
          InstantiationException, IllegalAccessException, NoSuchFieldException,
          ClassNotFoundException, CASException {
    List<Set<TopWrapper<?>>> wrappers = Lists.newArrayList();
    for (int type : types) {
      if (!type2wrappers.containsKey(type)) {
        addClassWrappersToIndex(jcas, type);
      }
      wrappers.add(type2wrappers.get(type));
    }
    return wrappers;
  }

  /**
   * 
   * @param types
   * @return
   * @throws AnalysisEngineProcessException
   * @throws IllegalArgumentException
   * @throws SecurityException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws NoSuchFieldException
   * @throws ClassNotFoundException
   * @throws CASException
   * 
   * @deprecated
   * @see #getWrappersByTypes(JCas, List)
   */
  @Deprecated
  public List<Set<TopWrapper<? extends TOP>>> getWrappersByTypes(List<Integer> types)
          throws AnalysisEngineProcessException, IllegalArgumentException, SecurityException,
          InstantiationException, IllegalAccessException, NoSuchFieldException,
          ClassNotFoundException, CASException {
    List<Set<TopWrapper<?>>> wrappers = Lists.newArrayList();
    for (int type : types) {
      if (!type2wrappers.containsKey(type)) {
        addClassWrappersToIndex(type);
      }
      wrappers.add(type2wrappers.get(type));
    }
    return wrappers;
  }

  private void addClassWrappersToIndex(JCas jcas, int type) throws AnalysisEngineProcessException,
          IllegalArgumentException, SecurityException, InstantiationException,
          IllegalAccessException, NoSuchFieldException, ClassNotFoundException, CASException {
    if (type2wrappers.containsKey(type)) {
      return;
    }
    type2wrappers.putAll(type, WrapperHelper.wrapAllFromJCas(this, jcas, type));
  }

  /**
   * 
   * @param type
   * @throws AnalysisEngineProcessException
   * @throws IllegalArgumentException
   * @throws SecurityException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws NoSuchFieldException
   * @throws ClassNotFoundException
   * @throws CASException
   * 
   * @deprecated @
   */
  @Deprecated
  private void addClassWrappersToIndex(int type) throws AnalysisEngineProcessException,
          IllegalArgumentException, SecurityException, InstantiationException,
          IllegalAccessException, NoSuchFieldException, ClassNotFoundException, CASException {
    if (type2wrappers.containsKey(type)) {
      return;
    }
    type2wrappers.putAll(type, WrapperHelper.wrapAllFromJCas(this, jcas, type));
  }

  public boolean checkWrapped(TOP top) {
    return topAddress2wrapper.containsKey(top.getAddress());
  }

  public TopWrapper<?> getWrapped(TOP top) {
    return topAddress2wrapper.get(top.getAddress());
  }

  public void addWrapped(TOP top, TopWrapper<? extends TOP> wrapper) {
    topAddress2wrapper.put(top.getAddress(), wrapper);
  }

  public void removeWrapped(TOP top) {
    topAddress2wrapper.remove(top.getAddress());
  }

  public void removeWrapped(TopWrapper<? extends TOP> wrapper) {
    while (topAddress2wrapper.values().remove(wrapper))
      ;
  }

  public boolean checkUnwrapped(TopWrapper<? extends TOP> wrapper) {
    return wrapperHashcode2top.containsKey(System.identityHashCode(wrapper));
  }

  public TOP getUnwrapped(TopWrapper<? extends TOP> wrapper) {
    return wrapperHashcode2top.get(System.identityHashCode(wrapper));
  }

  public void addUnwrapped(TopWrapper<? extends TOP> wrapper, TOP top) {
    wrapperHashcode2top.put(System.identityHashCode(wrapper), top);
  }

  public void removeUnwrapped(TopWrapper<? extends TOP> wrapper) {
    wrapperHashcode2top.remove(System.identityHashCode(wrapper));
  }

  public void removeUnwrapped(TOP top) {
    while (wrapperHashcode2top.values().remove(top))
      ;
  }

  @Deprecated
  public JCas getJCas() {
    return jcas;
  }

  @Deprecated
  public void setJCas(JCas jcas) {
    this.jcas = jcas;
  }

  public Map<Integer, TOP> getWrapperHashcode2top() {
    return wrapperHashcode2top;
  }

  public void setWrapperHashcode2top(BiMap<Integer, TOP> wrapperHashcode2top) {
    this.wrapperHashcode2top = wrapperHashcode2top;
  }

  public Map<Integer, TopWrapper<? extends TOP>> getTopAddress2wrapper() {
    return topAddress2wrapper;
  }

  public void setTopAddress2wrapper(BiMap<Integer, TopWrapper<? extends TOP>> topAddress2wrapper) {
    this.topAddress2wrapper = topAddress2wrapper;
  }

}