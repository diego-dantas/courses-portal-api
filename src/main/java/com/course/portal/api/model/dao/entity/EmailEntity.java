package com.course.portal.api.model.dao.entity;

import org.springframework.core.serializer.Serializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ConfigEmail")
public class EmailEntity implements Serializable{

    private static final Long sezializable = 1l;

    private long _id;
    private ProviderEntity provider;
    private String email;
    private String password;
    private int port;
    private String hostname;
    private boolean utilizaSsl;
    private boolean utilizaCredenciais;
    private boolean status;
    private Date dateCreate;
    private Date dateUpdate;

    public EmailEntity() {
    }

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

    @Column(name = "email", length = 300, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", length = 100, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "port", nullable = false)
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Column(name = "host_name", length = 300, nullable = false)
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Column(name = "ultiliza_ssl", nullable = false)
    public boolean getUtilizaSsl() {
        return utilizaSsl;
    }

    public void setUtilizaSsl(boolean utilizaSsl) {
        this.utilizaSsl = utilizaSsl;
    }

    @Column(name = "ultiliza_credenciais", nullable = false)
    public boolean getUtilizaCredenciais() {
        return utilizaCredenciais;
    }

    public void setUtilizaCredenciais(boolean utilizaCredenciais) {
        this.utilizaCredenciais = utilizaCredenciais;
    }

    @Column(name = "status", nullable = false)
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
