package com.course.portal.api.model.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "grid")
public class GridEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long _id;
    private ProviderEntity provider;
    private String description;
   // private List<SubGridEntity> subGrid;
   // private List<CourseEntity> course;
   // private List<GridCourseEntity> gridCourses;
    private Date dateCreate;
    private Date dateUpdate;

    public GridEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public ProviderEntity getProvider() {
        return provider;
    }

    public void setProvider(ProviderEntity provider) {
        this.provider = provider;
    }

    @Column(name = "description", length = 1000, nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
/*
    @ManyToMany
    @JoinTable(name = "sub_grid_grid",
                joinColumns = {@JoinColumn(name = "grid_id")},
                inverseJoinColumns = {@JoinColumn(name = "subGrid_id")})
    public List<SubGridEntity> getSubGrid() {
        return subGrid;
    }

    public void setSubGrid(List<SubGridEntity> subGrid) {
        this.subGrid = subGrid;
    }

    @ManyToMany(mappedBy = "grid")
    public List<CourseEntity> getCourse() {
        return course;
    }

    public void setCourse(List<CourseEntity> course) {
        this.course = course;
    }
    
    
    @OneToMany(mappedBy = "grid", cascade = CascadeType.ALL)
    public List<GridCourseEntity> getGridCourses() {
		return gridCourses;
	}

	public void setGridCourses(List<GridCourseEntity> gridCourses) {
		this.gridCourses = gridCourses;
	}

    
  */  
    
    //Date of create or/and update
    
    
   
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
