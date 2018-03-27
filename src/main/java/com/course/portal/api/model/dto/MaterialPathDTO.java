package com.course.portal.api.model.dto;

import com.course.portal.api.model.dao.entity.MaterialEntity;

public class MaterialPathDTO {

    private Long _id;
    private String imagePath;
    private MaterialDTO material;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public MaterialDTO getMaterial() {
        return material;
    }

    public void setMaterial(MaterialDTO material) {
        this.material = material;
    }
}
