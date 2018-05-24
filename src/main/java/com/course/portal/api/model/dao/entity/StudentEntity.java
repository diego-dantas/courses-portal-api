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
	private String cellPhone;
	private char   sexo;
	private char   news;
	private String rg;
	private String cpf;
	private String outro;
	private String comple;
	private String state;
    private boolean status;
	private String city;
	private PlanEntity plan;
	private ProfileEntity profile;
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
	
	@Column(name = "imagePath", length = 1000)
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Column(name = "zipCode", length = 50)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "street", length = 300)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "number", length = 50)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Column(name = "neighborhood", length = 100)
	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "status")
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
	
	@Column(name = "city", length = 100)
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	

    
    @Column(name = "date_update")
    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

	@Column(name = "cellPhone", length = 50)
	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	@Column(name = "sexo")
	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Column(name = "news")
	public char getNews() {
		return news;
	}

	public void setNews(char news) {
		this.news = news;
	}

	@Column(name = "rg", length = 100)
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Column(name = "cpf", length = 100)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "outro", length = 50)
	public String getOutro() {
		return outro;
	}

	public void setOutro(String outro) {
		this.outro = outro;
	}

	@Column(name = "comple", length = 1000)
	public String getComple() {
		return comple;
	}

	public void setComple(String comple) {
		this.comple = comple;
	}

	@Column(name = "state", length = 50)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	@ManyToOne(fetch = FetchType.EAGER)
	public ProfileEntity getProfile() {
		return profile;
	}

	public void setProfile(ProfileEntity profile) {
		this.profile = profile;
	}

}
