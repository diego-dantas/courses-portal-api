package com.course.portal.api.model.dto;

public class NewsletterDTO {
    
    private long    _id;
    private String  name;
    private String  email;
    private Boolean status;


    public long get_id(){
        return _id;
    }

    public void set_id(long _id){
        this._id = _id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Boolean isStatus(){
        return status;
    }

    public void setStatus(Boolean status){
        this.status = status;
    }
}
