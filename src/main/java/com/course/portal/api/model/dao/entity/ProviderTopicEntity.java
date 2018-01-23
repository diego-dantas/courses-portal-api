package com.course.portal.api.model.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "provider_topic")
public class ProviderTopicEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private long _id;
    private ProviderEntity provider;
    private String header;
    private String description;
    private Date dateCreate;
    private Date dateUpdate;

    public ProviderTopicEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public ProviderEntity getProvider() {
        return provider;
    }

    public void setProvider(ProviderEntity provider) {
        this.provider = provider;
    }

    @Column(name = "hearder", length = 1000, nullable = false)
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Column(name = "description", length = 5000, nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
