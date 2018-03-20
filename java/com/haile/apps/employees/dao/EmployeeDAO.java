package com.haile.apps.employees.dao;

import java.util.List;

import com.haile.apps.employees.entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> getEmployeesList();
	
	public Employee getEmployeeById(Integer id);
	
	public Integer getHighestID();
	
	public Integer createEmployee(String name, String company, String jobTitle);
	
	public Employee updateEmployee(int id, String name, String company, String jobTitle);
	
	public boolean deleteEmployee(Integer id);
	
}
