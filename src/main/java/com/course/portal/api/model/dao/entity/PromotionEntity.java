package com.course.portal.api.model.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "promotion")
public class PromotionEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private long _id;
	private String description;
	private Date dateInicial;
	private Date dateFinal;
	private double percentual;
	private String codigoCupom;
	private Date dateCreate;
    private Date dateUpdate;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long get_id() {
		return _id;
	}
	public void set_id(Long _id) {
		this._id = _id;
	}
	
	@Column(name = "description", length = 1000, nullable = false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "date_start", nullable = false)
	public Date getDateInicial() {
		return dateInicial;
	}
	public void setDateInicial(Date dateInicial) {
		this.dateInicial = dateInicial;
	}
	
	@Column(name = "date_finish", nullable = false)
	public Date getDateFinal() {
		return dateFinal;
	}
	public void setDateFinal(Date dateFinal) {
		this.dateFinal = dateFinal;
	}
	
	@Column(name = "percentual", nullable = false)
	public double getPercentual() {
		return percentual;
	}
	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}
	
	@Column(name = "codigo_cupom", length = 50, nullable = false)
	public String getCodigoCupom() {
		return codigoCupom;
	}
	public void setCodigoCupom(String codigoCupom) {
		this.codigoCupom = codigoCupom;
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
