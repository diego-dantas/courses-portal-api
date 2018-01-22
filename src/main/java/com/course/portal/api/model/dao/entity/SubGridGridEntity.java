package com.course.portal.api.model.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "sub_grid_grid")
public class SubGridGridEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long _id;
	private GridEntity grid;
	private SubGridEntity subGrid;
	private Date dateCreate;
	private Date dateUpdate;
	
	public SubGridGridEntity() {}
	
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

	public void setGridEntity(GridEntity grid) {
		this.grid = grid;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	public SubGridEntity getSubGrid() {
		return subGrid;
	}

	public void setSubGrid(SubGridEntity subGrid) {
		this.subGrid = subGrid;
	}
	
	@Column(name = "date_create", nullable = false)
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
