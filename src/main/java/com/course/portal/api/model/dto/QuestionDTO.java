package com.course.portal.api.model.dto;

public class QuestionDTO {

    private Long        _id;
    private String      description;
    private String      alterA;
    private String      alterB;
    private String      alterC;
    private String      alterD;
    private String      alterE;
    private int         correct;
    private boolean     status;
    private String wayImage;
    private StepsDTO    steps;


    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlterA() {
        return alterA;
    }

    public void setAlterA(String alterA) {
        this.alterA = alterA;
    }

    public String getAlterB() {
        return alterB;
    }

    public void setAlterB(String alterB) {
        this.alterB = alterB;
    }

    public String getAlterC() {
        return alterC;
    }

    public void setAlterC(String alterC) {
        this.alterC = alterC;
    }

    public String getAlterD() {
        return alterD;
    }

    public void setAlterD(String alterD) {
        this.alterD = alterD;
    }

    public String getAlterE() {
        return alterE;
    }

    public void setAlterE(String alterE) {
        this.alterE = alterE;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public StepsDTO getSteps() {
        return steps;
    }

    public void setSteps(StepsDTO steps) {
        this.steps = steps;
    }

    public String getWayImage() {
        return wayImage;
    }

    public void setWayImage(String wayImage) {
        this.wayImage = wayImage;
    }
}
