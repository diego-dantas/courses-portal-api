package com.course.portal.api.model.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "newsletter")
public class NewsletterEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private long _id;
    private String name;
    private String email;
	private boolean status;
    private Date dateUpdate;
	
	public NewsletterEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	
	@Column(name = "name", length = 300)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    @Column(name = "email", length = 300, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
        this.dateUpdate = dateToday;
    }

    @PreUpdate
    public void preUpdate(){
        dateUpdate = new Date();
    }

}
