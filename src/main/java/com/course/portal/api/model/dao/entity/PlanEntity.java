package com.course.portal.api.model.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "plan")
public class PlanEntity implements Serializable{


	private static final long serialVersionUID = 1L;

	private Long _id;
	private String description;
	private String wayImagen;
	private boolean status;
	private List<StudentEntity> student;
	private Date dateCreate;
    private Date dateUpdate;
    
	public PlanEntity(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long get_id() {
		return _id;
	}
	public void set_id(Long _id) {
		this._id = _id;
	}
	
	@Column(name = "description", length = 1000, nullable = false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "way_imagen", length = 200, nullable = false)
	public String getWayImagen() {
		return wayImagen;
	}
	public void setWayImagen(String wayImagen) {
		this.wayImagen = wayImagen;
	}
	
	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@OneToMany(mappedBy = "plan", fetch = FetchType.LAZY)
	public List<StudentEntity> getStudent() {
		return student;
	}

	public void setStudent(List<StudentEntity> student) {
		this.student = student;
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
