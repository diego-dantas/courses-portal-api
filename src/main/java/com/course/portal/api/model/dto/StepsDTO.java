package com.course.portal.api.model.dto;



public class StepsDTO {

    private long _id;
    private CourseDTO course;
    private int stepsOrder;
    private String name;
    private String description;

    public CourseDTO getCourse() {
        return course;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public int getStepsOrder() {
        return stepsOrder;
    }

    public void setStepsOrder(int stepsOrder) {
        this.stepsOrder = stepsOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
