package com.course.portal.api.model.dao.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "evaluation")
public class EvaluationEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long        _id;
    private String      name;
    private boolean     status;
    private StepsEntity steps;
    private List<EvaluationQuestionEntity> evaluationQuestion;
    private Date        dateUpdate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    @Column(name = "name", length = 100, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "status", nullable = false)
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public StepsEntity getSteps() {
        return steps;
    }

    public void setSteps(StepsEntity steps) {
        this.steps = steps;
    }

    @OneToMany(mappedBy = "evaluation", fetch = FetchType.LAZY)
    public List<EvaluationQuestionEntity> getEvaluationQuestion() {
        return evaluationQuestion;
    }

    public void setEvaluationQuestion(List<EvaluationQuestionEntity> evaluationQuestion) {
        this.evaluationQuestion = evaluationQuestion;
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
