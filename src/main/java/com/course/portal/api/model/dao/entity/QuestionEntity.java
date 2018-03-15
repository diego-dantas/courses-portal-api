package com.course.portal.api.model.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "question")
public class QuestionEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long        _id;
    private String      description;
    private String      alterA;
    private String      alterB;
    private String      alterC;
    private String      alterD;
    private String      alterE;
    private int         correct;
    private boolean     status;
    private StepsEntity steps;
    private Date        dateUpdate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    @Column(name = "description", length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "alterA", length = 1000)
    public String getAlterA() {
        return alterA;
    }

    public void setAlterA(String alterA) {
        this.alterA = alterA;
    }

    @Column(name = "alterB", length = 1000)
    public String getAlterB() {
        return alterB;
    }

    public void setAlterB(String alterB) {
        this.alterB = alterB;
    }

    @Column(name = "alterC", length = 1000)
    public String getAlterC() {
        return alterC;
    }

    public void setAlterC(String alterC) {
        this.alterC = alterC;
    }

    @Column(name = "alterD", length = 1000)
    public String getAlterD() {
        return alterD;
    }

    public void setAlterD(String alterD) {
        this.alterD = alterD;
    }

    @Column(name = "alterE", length = 1000)
    public String getAlterE() {
        return alterE;
    }

    public void setAlterE(String alterE) {
        this.alterE = alterE;
    }

    @Column(name = "correct", length = 1000)
    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
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
