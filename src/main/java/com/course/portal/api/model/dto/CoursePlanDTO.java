package com.course.portal.api.model.dto;

import com.course.portal.api.model.dao.entity.CourseEntity;
import com.course.portal.api.model.dao.entity.PlanEntity;

public class CoursePlanDTO {

    private long      _id;
    private CourseDTO course;
    private PlanDTO   plan;
    private double    price;
    private int       percentage;


    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public PlanDTO getPlan() {
        return plan;
    }

    public void setPlan(PlanDTO plan) {
        this.plan = plan;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
