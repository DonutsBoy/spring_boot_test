package com.example.demo.model;

import lombok.Data;

@Data
public class UserProfileModel {
	
	private String id;
	private String name;
	private String phone;
	private String address;
	
	public UserProfileModel(String id, String name, String phone, String address) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}	
	
}
