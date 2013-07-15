package edu.cmu.lti.oaqa.baseqa.data.nlp;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.oaqa.model.input.Question;

import com.google.common.base.Objects;

import edu.cmu.lti.oaqa.baseqa.data.gerp.GerpAnnotationWrapper;

public class QuestionWrapper extends GerpAnnotationWrapper<Question> {

  private static final long serialVersionUID = 1L;

  public static enum QuestionType {
    FACTOID, DEFINITION, MULTI_SENTENCE, COMPOUND, ABBREVIATION, UNCLASSIFIED, LIST, OPINION
  };

  private String id;

  private String source;

  private String text;

  private QuestionType questionType;

  public QuestionWrapper(int begin, int end, String id, String source, String text,
          QuestionType questionType) {
    super(begin, end);
    this.id = id;
    this.source = source;
    this.text = text;
    this.questionType = questionType;
  }

  public QuestionWrapper(int begin, int end, String id, String source, String text,
          QuestionType questionClass, String generator) {
    super(begin, end, generator);
    this.id = id;
    this.source = source;
    this.text = text;
    this.questionType = questionClass;
  }

  @Override
  public Class<? extends Question> getTypeClass() {
    return Question.class;
  }

  @Override
  public void wrap(Question annotation) throws AnalysisEngineProcessException {
    super.wrap(annotation);
    this.id = annotation.getId();
    this.source = annotation.getSource();
    this.text = annotation.getText();
    this.questionType = QuestionType.valueOf(annotation.getQuestionType());
  }

  @Override
  public Question unwrap(JCas jcas) throws AnalysisEngineProcessException {
    Question annotation = super.unwrap(jcas);
    annotation.setId(id);
    annotation.setSource(source);
    annotation.setText(text);
    annotation.setQuestionType(questionType.toString());
    return annotation;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), text);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    QuestionWrapper other = (QuestionWrapper) obj;
    return Objects.equal(this.text, other.text);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public QuestionType getQuestionClass() {
    return questionType;
  }

  public void setQuestionClass(QuestionType questionClass) {
    this.questionType = questionClass;
  }

}
