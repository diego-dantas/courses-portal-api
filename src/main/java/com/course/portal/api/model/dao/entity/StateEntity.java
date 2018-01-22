package com.course.portal.api.model.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "state")
public class StateEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long _id;
	private String state;
	private List<CityEntity> city;
	
	public StateEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}
	
	@Column(name = "state", length = 100, nullable = false)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	public List<CityEntity> getCityEntity() {
		return city;
	}
	
	public void setCityEntity(List<CityEntity> city) {
		this.city = city;
	}
	
}
