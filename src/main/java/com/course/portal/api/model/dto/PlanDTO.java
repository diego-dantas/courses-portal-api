package com.course.portal.api.model.dto;

public class PlanDTO {

    private Long _id;
    private String description;
    private String wayImage;
    private boolean status;

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

    public String getWayImage() {
        return wayImage;
    }

    public void setWayImage(String wayImagen) {
        this.wayImage = wayImagen;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
