package com.course.portal.api.model.dao.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity
//@Table(name = "grid_course")
public class GridCourseEntity implements Serializable {

/*	private static final long serialVersionUID = 1L;
	
	private long _id;
	private GridEntity grid;
	private CourseEntity courses;
	
	public GridCourseEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "grid_id")
	public GridEntity getGrid() {
		return grid;
	}

	public void setGrid(GridEntity grid) {
		this.grid = grid;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "courses_id")
	public CourseEntity getCourses() {
		return courses;
	}

	public void setCourses(CourseEntity courses) {
		this.courses = courses;
	}
	
	*/

}
