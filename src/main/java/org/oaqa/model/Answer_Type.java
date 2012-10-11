
/* First created by JCasGen Mon Oct 08 18:56:44 EDT 2012 */
package org.oaqa.model;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** An answer generated by the QA system.
<<<<<<< HEAD
 * Updated by JCasGen Thu Oct 11 12:34:47 EDT 2012
=======
 * Updated by JCasGen Tue Oct 09 21:34:48 EDT 2012
>>>>>>> b546a96e32521e5cc420b3a114ad3fb1279252a1
 * @generated */
public class Answer_Type extends OAQATop_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Answer_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Answer_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Answer(addr, Answer_Type.this);
  			   Answer_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Answer(addr, Answer_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Answer.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.oaqa.model.Answer");
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "org.oaqa.model.Answer");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "org.oaqa.model.Answer");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_searchResultList;
  /** @generated */
  final int     casFeatCode_searchResultList;
  /** @generated */ 
  public int getSearchResultList(int addr) {
        if (featOkTst && casFeat_searchResultList == null)
      jcas.throwFeatMissing("searchResultList", "org.oaqa.model.Answer");
    return ll_cas.ll_getRefValue(addr, casFeatCode_searchResultList);
  }
  /** @generated */    
  public void setSearchResultList(int addr, int v) {
        if (featOkTst && casFeat_searchResultList == null)
      jcas.throwFeatMissing("searchResultList", "org.oaqa.model.Answer");
    ll_cas.ll_setRefValue(addr, casFeatCode_searchResultList, v);}
    
   /** @generated */
  public int getSearchResultList(int addr, int i) {
        if (featOkTst && casFeat_searchResultList == null)
      jcas.throwFeatMissing("searchResultList", "org.oaqa.model.Answer");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_searchResultList), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_searchResultList), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_searchResultList), i);
  }
   
  /** @generated */ 
  public void setSearchResultList(int addr, int i, int v) {
        if (featOkTst && casFeat_searchResultList == null)
      jcas.throwFeatMissing("searchResultList", "org.oaqa.model.Answer");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_searchResultList), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_searchResultList), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_searchResultList), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_score;
  /** @generated */
  final int     casFeatCode_score;
  /** @generated */ 
  public double getScore(int addr) {
        if (featOkTst && casFeat_score == null)
      jcas.throwFeatMissing("score", "org.oaqa.model.Answer");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_score);
  }
  /** @generated */    
  public void setScore(int addr, double v) {
        if (featOkTst && casFeat_score == null)
      jcas.throwFeatMissing("score", "org.oaqa.model.Answer");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_score, v);}
    
  
 
  /** @generated */
  final Feature casFeat_variants;
  /** @generated */
  final int     casFeatCode_variants;
  /** @generated */ 
  public int getVariants(int addr) {
        if (featOkTst && casFeat_variants == null)
      jcas.throwFeatMissing("variants", "org.oaqa.model.Answer");
    return ll_cas.ll_getRefValue(addr, casFeatCode_variants);
  }
  /** @generated */    
  public void setVariants(int addr, int v) {
        if (featOkTst && casFeat_variants == null)
      jcas.throwFeatMissing("variants", "org.oaqa.model.Answer");
    ll_cas.ll_setRefValue(addr, casFeatCode_variants, v);}
    
   /** @generated */
  public String getVariants(int addr, int i) {
        if (featOkTst && casFeat_variants == null)
      jcas.throwFeatMissing("variants", "org.oaqa.model.Answer");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_variants), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_variants), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_variants), i);
  }
   
  /** @generated */ 
  public void setVariants(int addr, int i, String v) {
        if (featOkTst && casFeat_variants == null)
      jcas.throwFeatMissing("variants", "org.oaqa.model.Answer");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_variants), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_variants), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_variants), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_features;
  /** @generated */
  final int     casFeatCode_features;
  /** @generated */ 
  public int getFeatures(int addr) {
        if (featOkTst && casFeat_features == null)
      jcas.throwFeatMissing("features", "org.oaqa.model.Answer");
    return ll_cas.ll_getRefValue(addr, casFeatCode_features);
  }
  /** @generated */    
  public void setFeatures(int addr, int v) {
        if (featOkTst && casFeat_features == null)
      jcas.throwFeatMissing("features", "org.oaqa.model.Answer");
    ll_cas.ll_setRefValue(addr, casFeatCode_features, v);}
    
  
 
  /** @generated */
  final Feature casFeat_featureVector;
  /** @generated */
  final int     casFeatCode_featureVector;
  /** @generated */ 
  public int getFeatureVector(int addr) {
        if (featOkTst && casFeat_featureVector == null)
      jcas.throwFeatMissing("featureVector", "org.oaqa.model.Answer");
    return ll_cas.ll_getRefValue(addr, casFeatCode_featureVector);
  }
  /** @generated */    
  public void setFeatureVector(int addr, int v) {
        if (featOkTst && casFeat_featureVector == null)
      jcas.throwFeatMissing("featureVector", "org.oaqa.model.Answer");
    ll_cas.ll_setRefValue(addr, casFeatCode_featureVector, v);}
    
   /** @generated */
  public double getFeatureVector(int addr, int i) {
        if (featOkTst && casFeat_featureVector == null)
      jcas.throwFeatMissing("featureVector", "org.oaqa.model.Answer");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_featureVector), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_featureVector), i);
  return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_featureVector), i);
  }
   
  /** @generated */ 
  public void setFeatureVector(int addr, int i, double v) {
        if (featOkTst && casFeat_featureVector == null)
      jcas.throwFeatMissing("featureVector", "org.oaqa.model.Answer");
    if (lowLevelTypeChecks)
      ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_featureVector), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_featureVector), i);
    ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_featureVector), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Answer_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_searchResultList = jcas.getRequiredFeatureDE(casType, "searchResultList", "uima.cas.FSArray", featOkTst);
    casFeatCode_searchResultList  = (null == casFeat_searchResultList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_searchResultList).getCode();

 
    casFeat_score = jcas.getRequiredFeatureDE(casType, "score", "uima.cas.Double", featOkTst);
    casFeatCode_score  = (null == casFeat_score) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_score).getCode();

 
    casFeat_variants = jcas.getRequiredFeatureDE(casType, "variants", "uima.cas.StringArray", featOkTst);
    casFeatCode_variants  = (null == casFeat_variants) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_variants).getCode();

 
    casFeat_features = jcas.getRequiredFeatureDE(casType, "features", "uima.cas.FSList", featOkTst);
    casFeatCode_features  = (null == casFeat_features) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_features).getCode();

 
    casFeat_featureVector = jcas.getRequiredFeatureDE(casType, "featureVector", "uima.cas.DoubleArray", featOkTst);
    casFeatCode_featureVector  = (null == casFeat_featureVector) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_featureVector).getCode();

  }
}



    