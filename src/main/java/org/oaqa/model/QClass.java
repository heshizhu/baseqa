

/* First created by JCasGen Mon Oct 08 18:56:44 EDT 2012 */
package org.oaqa.model;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** The class of the question, determined by an automatic question classification process.
<<<<<<< HEAD
 * Updated by JCasGen Thu Oct 11 12:34:48 EDT 2012
=======
 * Updated by JCasGen Tue Oct 09 21:34:49 EDT 2012
>>>>>>> b546a96e32521e5cc420b3a114ad3fb1279252a1
 * XML source: /home/yangzi/QA/baseqa/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class QClass extends OAQAAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(QClass.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected QClass() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public QClass(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public QClass(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public QClass(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: qClass

  /** getter for qClass - gets The kind (class) of the question.
   * @generated */
  public String getQClass() {
    if (QClass_Type.featOkTst && ((QClass_Type)jcasType).casFeat_qClass == null)
      jcasType.jcas.throwFeatMissing("qClass", "org.oaqa.model.QClass");
    return jcasType.ll_cas.ll_getStringValue(addr, ((QClass_Type)jcasType).casFeatCode_qClass);}
    
  /** setter for qClass - sets The kind (class) of the question. 
   * @generated */
  public void setQClass(String v) {
    if (QClass_Type.featOkTst && ((QClass_Type)jcasType).casFeat_qClass == null)
      jcasType.jcas.throwFeatMissing("qClass", "org.oaqa.model.QClass");
    jcasType.ll_cas.ll_setStringValue(addr, ((QClass_Type)jcasType).casFeatCode_qClass, v);}    
  }

    