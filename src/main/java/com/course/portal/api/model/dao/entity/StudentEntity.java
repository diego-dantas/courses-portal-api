package com.course.portal.api.model.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
public class StudentEntity implements Serializable{

  	private static final long serialVersionUID = 1L;




    private long _id;
    private String name;
    private String email;
    private String password;
    private String imagePath;
    private String zipCode;
    private String street;
    private String number;
    private String neighborhood;
    private String phone;
    private boolean status;
    private PlanEntity plan;
    private CityEntity city;
    private Date dateCreate;
    private Date dateUpdate;


    public StudentEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }    
    
    @Column(name = "name", length = 200, nullable = false)
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "email", length = 200, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "password", length = 1000, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	public PlanEntity getPlan() {
		return plan;
	}

	public void setPlan(PlanEntity plan) {
		this.plan = plan;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	public CityEntity getCity() {
		return city;
	}
	
	public void setCity(CityEntity city) {
		this.city = city;
	}
	
	@Column(name = "date_create")
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
    
    @Column(name = "date_update")
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
