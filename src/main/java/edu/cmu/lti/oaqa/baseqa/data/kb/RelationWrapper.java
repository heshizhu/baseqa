package edu.cmu.lti.oaqa.baseqa.data.kb;

import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.oaqa.model.kb.Concept;
import org.oaqa.model.kb.Relation;

import com.google.common.base.Objects;

import edu.cmu.lti.oaqa.baseqa.data.core.WrapperHelper;

public class RelationWrapper extends ConceptWrapper {

  private static final long serialVersionUID = 1L;

  private List<EntityWrapper> arguments;

  public RelationWrapper(String name, List<String> ids, List<ConceptMentionWrapper> mentions,
          List<EntityWrapper> arguments) {
    super(name, ids, mentions);
    this.arguments = arguments;
  }

  public RelationWrapper(String name, List<String> ids, List<ConceptMentionWrapper> mentions,
          List<EntityWrapper> arguments, String generator) {
    super(name, ids, mentions, generator);
    this.arguments = arguments;
  }

  @Override
  public Class<? extends Relation> getTypeClass() {
    return Relation.class;
  }

  @Override
  public void wrap(Concept top) throws AnalysisEngineProcessException {
    super.wrap(top);
    try {
      this.arguments = WrapperHelper.wrapTopList(((Relation) top).getArguments(),
              EntityWrapper.class);
    } catch (Exception e) {
      throw new AnalysisEngineProcessException(e);
    }
  }

  @Override
  public Relation unwrap(JCas jcas) throws AnalysisEngineProcessException {
    Relation top = (Relation) super.unwrap(jcas);
    top.setArguments(WrapperHelper.unwrapTopList(arguments, jcas));
    return top;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), arguments);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RelationWrapper other = (RelationWrapper) obj;
    return Objects.equal(arguments, other.arguments);
  }

  public List<EntityWrapper> getArguments() {
    return arguments;
  }

  public void setArguments(List<EntityWrapper> arguments) {
    this.arguments = arguments;
  }

}