package com.course.portal.api.model.dao.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "material")
public class MaterialEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long _id;
    private int materialOrder;
    private String name;
    private String type;
    private String url;
    private boolean download;
    private boolean status;
    private StepsEntity steps;
    private List<MaterialPathEntity> materialPath;
    private Date dateCreate;
    private Date dateUpdate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    @Column(name = "material_order", nullable = false)
    public int getMaterialOrder() {
        return materialOrder;
    }

    public void setMaterialOrder(int materialOrder) {
        this.materialOrder = materialOrder;
    }

    @Column(name = "name", length = 100, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type",  length = 100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "url", length = 500)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "download", nullable = false)
    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
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

    @OneToMany(mappedBy = "material", fetch = FetchType.LAZY)
    public List<MaterialPathEntity> getMaterialPath() {
        return materialPath;
    }

    public void setMaterialPath(List<MaterialPathEntity> materialPath) {
        this.materialPath = materialPath;
    }

    @Column(name = "date_create")
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
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
        this.dateCreate = dateToday;
        this.dateUpdate = dateToday;
    }

    @PreUpdate
    public void preUpdate(){
        dateUpdate = new Date();
    }
}
