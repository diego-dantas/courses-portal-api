package com.course.portal.api.model.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "evaluation_question")
public class EvaluationQuestionEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long             _id;
    private EvaluationEntity evaluation;
    private QuestionEntity   question;
    private Date dateUpdate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public EvaluationEntity getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EvaluationEntity evaluation) {
        this.evaluation = evaluation;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    @Column(name = "date_update", nullable = false)
    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @PrePersist
    public void preCreate(){
        final Date dateToday = new Date();
        this.dateUpdate = dateToday;
    }

    @PreUpdate
    public void preUpdate(){
        dateUpdate = new Date();
    }
}
