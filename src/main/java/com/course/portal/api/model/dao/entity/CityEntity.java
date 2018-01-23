package com.course.portal.api.model.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class CityEntity implements Serializable {


	private static final long serialVersionUID = 1L;

	private Long _id;
	private String city;
	private StateEntity state;
	private List<StudentEntity> student;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long get_id() {
		return _id;
	}
	public void set_id(Long _id) {
		this._id = _id;
	}
	
	@Column(name = "city", length = 150, nullable = false)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	public StateEntity getState() {
		return state;
	}
	public void setState(StateEntity state) {
		this.state = state;
	}
	
	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
	public List<StudentEntity> getStudent() {
		return student;
	}
	public void setStudent(List<StudentEntity> student) {
		this.student = student;
	}
	
	

}
