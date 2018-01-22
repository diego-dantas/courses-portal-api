package com.course.portal.api.model.dao.entity;

import javax.persistence.*;

import com.course.portal.api.model.dao.entity.SubGridGridEntity;

import java.util.List;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sub_grid")
public class SubGridEntity implements Serializable{

    
	private static final long serialVersionUID = 1L;
	
	private Long _id;
    private ProviderEntity provider;
    private String description;
    private List<SubGridGridEntity> subGridGrid;
    private Date dateCreate;
    private Date dateUpdate;

    public SubGridEntity(){}

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

    @Column(name = "description", length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @OneToMany(mappedBy = "subGrid", fetch = FetchType.LAZY)
    public List<SubGridGridEntity> getSubGridGrid() {
		return subGridGrid;
	}

	public void setSubGridGrid(List<SubGridGridEntity> subGridGrid) {
		this.subGridGrid = subGridGrid;
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
