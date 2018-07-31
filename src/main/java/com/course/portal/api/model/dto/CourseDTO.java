package com.course.portal.api.model.dto;

public class CourseDTO {

    private long _id;
    private String name;
    private String description;
    private String objective;
    private int hours;
    private double price;
    private String wayImage;
    private boolean status;
    private int views;
    private int home;
    private GridDTO grid;
    private SubGridDTO subGrid;
    private String labelUrl;

    public long get_id() {
        return _id;
    }

	public int getHome() {
		return home;
	}

	public void setHome(int home) {
		this.home = home;
	}

	public void set_id(long _id) {
        this._id = _id;
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

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWayImage() {
        return wayImage;
    }

    public void setWayImage(String wayImage) {
        this.wayImage = wayImage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public GridDTO getGrid() {
        return grid;
    }

    public void setGrid(GridDTO grid) {
        this.grid = grid;
    }

    public SubGridDTO getSubGrid() {
        return subGrid;
    }

    public void setSubGrid(SubGridDTO subGrid) {
        this.subGrid = subGrid;
    }

    public String getLabelUrl() {
        return labelUrl;
    }

    public void setLabelUrl(String labelUrl) {
        this.labelUrl = labelUrl;
    }
}
