package com.course.portal.api.model.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "couse")
public class CourseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long _id;
	private Long id;
	private String name;
	private String description;
	private String objective;
	private int hours;
	private double price;
	private String wayImage;
	private boolean status;
	private int views;
	private List<GridEntity> grid;
	private List<SubGridEntity> subGrid;
	private Date dateCreate;
    private Date dateUpdate;
	
	public CourseEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}
	
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 100, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "description", length = 5000, nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "objective", length = 5000, nullable = false)
	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}
	
	@Column(name = "hours")
	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
	
	@Column(name = "price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column(name = "way_imagen", length = 200)
	public String getWayImage() {
		return wayImage;
	}

	public void setWayImage(String wayImage) {
		this.wayImage = wayImage;
	}
	
	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Column(name = "views")
	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	@ManyToMany
	@JoinTable(name = "grid_course",
				joinColumns = {@JoinColumn(name = "course_id")},
				inverseJoinColumns = {@JoinColumn(name = "grid_id")})
	public List<GridEntity> getGrid() {
		return grid;
	}

	public void setGrid(List<GridEntity> grid) {
		this.grid = grid;
	}

	@ManyToMany
	@JoinTable(name = "sub_grid_course",
			joinColumns = {@JoinColumn(name = "course_id")},
			inverseJoinColumns = {@JoinColumn(name = "subGrid_id")})
	public List<SubGridEntity> getSubGrid() {
		return subGrid;
	}

	public void setSubGrid(List<SubGridEntity> subGrid) {
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
