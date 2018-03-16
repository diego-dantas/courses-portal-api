package com.course.portal.api.model.dto;

public class EvaluationQuestionDTO {

    private Long          _id;
    private QuestionDTO   question;
    private EvaluationDTO evaluation;


    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
    }

    public EvaluationDTO getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EvaluationDTO evaluation) {
        this.evaluation = evaluation;
    }
}
