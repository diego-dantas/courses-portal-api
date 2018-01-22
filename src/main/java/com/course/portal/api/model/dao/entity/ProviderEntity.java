package com.course.portal.api.model.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Provaider")
public class ProviderEntity implements Serializable{

    private static final long sezializable = 1l;

    private long _id;
    private String name;
    private String email;
    private String password;
    private String welcome;
    private boolean status;
    private List<EmailEntity> emailEntity;
    private List<GridEntity> gridEntity;
    private Date dateCreate;
    private Date dateUpdate;

    public ProviderEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    @Column(length = 500, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 300, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 100, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(length = 1000, nullable = true)
    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    @Column(length = 1, nullable = false)
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    public List<EmailEntity> getEmailEntity() {
        return emailEntity;
    }

    public void setEmailEntity(List<EmailEntity> emailEntity) {
        this.emailEntity = emailEntity;
    }

    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    public List<GridEntity> getGridEntity() {
        return gridEntity;
    }

    public void setGridEntity(List<GridEntity> gridEntity) {
        this.gridEntity = gridEntity;
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
