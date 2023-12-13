package com.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="StudentRestAPI")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Student_id")
	private int id;
	@Column(name="Student_name",length=50)
	private String name;
	@Column(name="Student_address",length = 100)
	private String address;
	@Column(name="Student_phoneNo",length = 10,unique = true)
	private String phone_No;
	
	public Student() {
		super();
		
	}
	
	public Student(int id, String name, String address, String phone_No) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone_No = phone_No;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_No() {
		return phone_No;
	}

	public void setPhone_No(String phone_No) {
		this.phone_No = phone_No;
	}
	
	
}
