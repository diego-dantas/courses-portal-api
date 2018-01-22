package com.course.portal.api.model.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student")
public class StudentEntity implements Serializable{

    private static final long serializable = 1l;


    private long _id;
    private String name;
    private String email;
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


    @Column(name  = "name", nullable = false, length = 150)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email", nullable = false, length = 150)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + _id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateCreate=" + dateCreate +
                ", dateUpdate=" + dateUpdate +
                '}';
    }
}
