package com.airtel.mongo.models;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "employee")
public class Employee {

    @Id
    private String id;
    @NotBlank(message = "Name Can't be Empty")
    private String name;
    @NotBlank(message = "Department Can't be Empty")
    private String department;
    @NotBlank(message = "Designation Can't be Empty")
    private String designation;
    
    public Employee() {}
    
    
    public Employee(String name, String department, String designation) {
    		this.name = name;
    		this.department = department;
    		this.designation = designation;
    }
    public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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
    
    public String toString() {
    		return "name :" + this.name + ",department: " + this.name + ",designation: " + designation;
    }
}