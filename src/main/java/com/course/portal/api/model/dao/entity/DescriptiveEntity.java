package com.course.portal.api.model.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "descriptive")
public class DescriptiveEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long _id;
    private String title;
    private String content;
    private Date dateUpdate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    @Column(name = "title", length = 100, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content", length = 100, nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
