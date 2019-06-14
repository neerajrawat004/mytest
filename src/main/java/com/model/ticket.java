package com.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class ticket {
	
	@Id
	private String id;
	private String name;
	private String code;
	private String owner;
	
	
	public ticket() {
		// TODO Auto-generated constructor stub
	}
	public ticket(String id, String name, String code, String owner) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.owner = owner;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	

}
