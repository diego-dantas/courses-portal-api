package com.course.portal.api.model.dto;

public class MaterialDTO {

    private Long _id;
    private int materialOrder;
    private String name;
    private String type;
    private String url;
    private boolean download;
    private boolean status;
    private StepsDTO steps;


    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public int getMaterialOrder() {
        return materialOrder;
    }

    public void setMaterialOrder(int materialOrder) {
        this.materialOrder = materialOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
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
