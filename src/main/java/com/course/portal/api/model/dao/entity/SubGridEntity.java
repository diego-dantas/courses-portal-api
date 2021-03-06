package com.course.portal.api.model.dao.entity;

import javax.persistence.*;

import com.course.portal.api.model.dao.entity.SubGridGridEntity;

import java.util.List;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sub_grid")
public class SubGridEntity implements Serializable{
   
	private static final long serialVersionUID = 1L;
	
	private Long _id;
    private GridEntity grid;
    private String description;
    private String labelUrl;
    //private List<GridEntity> grid;
    private List<CourseEntity> course;
    private Date dateCreate;
    private Date dateUpdate;

    public SubGridEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public GridEntity getGrid() {
        return grid;
    }

    public void setGrid(GridEntity grid) {
        this.grid = grid;
    }

    @Column(name = "description", length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "labelUrl", length = 100)
    public String getLabelUrl() {
        return labelUrl;
    }

    public void setLabelUrl(String labelUrl) {
        this.labelUrl = labelUrl;
    }

    /*   @ManyToMany(mappedBy = "subGrid")
        public List<GridEntity> getGrid() {
            return grid;
        }

        public void setGrid(List<GridEntity> grid) {
            this.grid = grid;
        }
    */
    @OneToMany(mappedBy = "subGrid", fetch = FetchType.LAZY)
    public List<CourseEntity> getCourse() {
     return course;
 }

    public void setCourse(List<CourseEntity> course) {
        this.course = course;
    }

    @Column(name = "date_create")
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Column(name = "date_update")
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
