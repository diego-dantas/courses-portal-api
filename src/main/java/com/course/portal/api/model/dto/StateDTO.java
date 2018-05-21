package com.course.portal.api.model.dto;

public class StateDTO {

    private long _id;
	private String state;
    private String name;
	
	public long get_id() {
		return _id;
	}
	
	public void set_id(long _id) {
		this._id = _id;
    }
    
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
    }
    
	public void setName(String name) {
		this.name = name;
	}
    

}