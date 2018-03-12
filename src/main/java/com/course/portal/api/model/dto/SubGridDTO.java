package com.course.portal.api.model.dto;

public class SubGridDTO {

    private Long _id;
    private GridDTO grid;
    private String description;
    private String labelUrl;

    public SubGridDTO() {
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public GridDTO getGrid() {
        return grid;
    }

    public void setGrid(GridDTO grid) {
        this.grid = grid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabelUrl() {
        return labelUrl;
    }

    public void setLabelUrl(String labelUrl) {
        this.labelUrl = labelUrl;
    }

    @Override
    public String toString() {
        return "SubGridDTO{" +
                "_id=" + _id +
                ", grid=" + grid +
                ", description='" + description + '\'' +
                '}';
    }
}
