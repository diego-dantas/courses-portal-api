package com.course.portal.api.model.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class CourseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private long _id;
	private String name;
	private String description;
	private String objective;
	private int hours;
	private double price;
	private String wayImage;
	private boolean status;
	private int views;
	private List<StepsEntity> steps;
	private List<GridEntity> grid;
	//private List<SubGridEntity> subGrid;
	//private List<GridCourseEntity> gridCourses;
	private Date dateCreate;
    private Date dateUpdate;
	
	public CourseEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
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
	
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	public List<StepsEntity> getSteps() {
		return steps;
	}

	public void setSteps(List<StepsEntity> steps) {
		this.steps = steps;
	}

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	public List<GridEntity> getGrid() {
		return grid;
	}

	public void setGrid(List<GridEntity> grid) {
		this.grid = grid;
	}
	/*
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



        @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL)
        public List<GridCourseEntity> getGridCourses() {
            return gridCourses;
        }

        public void setGridCourses(List<GridCourseEntity> gridCourses) {
            this.gridCourses = gridCourses;
        }
    */
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
