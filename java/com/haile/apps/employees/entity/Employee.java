package com.haile.apps.employees.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "employees", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Employee {

	private int id;
	private String name;
	private String company;
	private String jobTitle;
	
	public Employee(){
		
	}
	
	public Employee(int id, String name, String company, String jobTitle){
		this.id = id;
		this.name = name;
		this.company = company;
		this.jobTitle = jobTitle;
	}
	
	/*
	 * Get and Set ID
	 */
	@Id
	@Column(name = "id")
	public int getId(){
        return id;
    }
 
    public void setId(int id){
        this.id = id;
    }
	
    /*
	 * Get and Set name
	 */
	@Column(name = "name")
	public String getName(){
        return name;
    }
 
    public void setName(String name){
        this.name = name;
    } 
    
    /*
	 * Get and Set company
	 */
	@Column(name = "company")
    public String getCompany(){
        return company;
    }
    
    public void setCompany(String company){
        this.company = company;
    }
    
    /*
	 * Get and Set jobTitle
	 */
	@Column(name = "jobTitle")
    public String getJobTitle(){
        return jobTitle;
    }
 
    public void setJobTitle(String jobTitle){
        this.jobTitle = jobTitle;
    }
	
}
