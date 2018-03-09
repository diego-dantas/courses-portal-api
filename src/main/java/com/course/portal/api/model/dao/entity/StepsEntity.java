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
@Table(name = "steps")
public class StepsEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long _id;
	private CourseEntity course;
	private int stepsOrder;
	private String name;
	private String description;
	private Date dateCreate;
    private Date dateUpdate;
	
	public StepsEntity() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
		this.course = course;
	}
	
	@Column(name = "steps_order", nullable = false)
	public int getStepsOrder() {
		return stepsOrder;
	}

	public void setStepsOrder(int stepsOrder) {
		this.stepsOrder = stepsOrder;
	}
	
	@Column(name = "name", length = 100, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "description", length = 2000, nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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


	@Override
	public String toString() {
		return "StepsEntity{" +
				"_id=" + _id +
				", course=" + course +
				", stepsOrder=" + stepsOrder +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", dateCreate=" + dateCreate +
				", dateUpdate=" + dateUpdate +
				'}';
	}
}
