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
@Table(name = "profile")
public class ProfileEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private long _id;
    private String description;
    private List<StudentEntity> student;
    private Date dataUpdate;

    public ProfileEntity() { }

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
    
    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
	public List<StudentEntity> getStudent() {
		return student;
	}

	public void setStudent(List<StudentEntity> student) {
		this.student = student;
	}

    @Column(name = "date_update")
    public Date getDateUpdate() {
        return dataUpdate;
    }

    public void setDateUpdate(Date dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    @PrePersist
    public void preCreate(){
        final Date dateToday = new Date();
        this.dataUpdate = dateToday;
    }

    @PreUpdate
    public void preUpdate(){
        dataUpdate = new Date();
    }
}