package com.course.portal.api.model.dto;

public class EvaluationDTO {

    private Long     _id;
    private String   name;
    private boolean  status;
    private StepsDTO steps;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
