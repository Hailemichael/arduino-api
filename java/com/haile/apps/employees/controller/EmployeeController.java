package com.haile.apps.employees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.haile.apps.employees.dao.EmployeeDAO;
import com.haile.apps.employees.entity.Employee;

//@EnableTransactionManagement // As <tx:annotation-config />
//@Transactional(readOnly = true) to enable transaction
@Controller
@EnableWebMvc
public class EmployeeController {
	@Autowired
	 private EmployeeDAO employeeDAO;
	 	 
	 @RequestMapping(value= { "/employees" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public @ResponseBody List<Employee> getEmployeesList() {
	     
	     return employeeDAO.getEmployeesList();
	 }
	 
	 @RequestMapping(value= { "/employees/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public @ResponseBody Employee getEmployeeById(@PathVariable("id") Integer id) {
	     
	     return employeeDAO.getEmployeeById(id);
	 }
	 
	 @RequestMapping(value= { "/employees/newest" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public @ResponseBody Integer getHighestID() {
	     
	     return employeeDAO.getHighestID();
	 }
	 
	 @RequestMapping(value= { "/employees/create" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public @ResponseBody Integer createEmployee(@RequestBody Employee employee) {
		 
	     return employeeDAO.createEmployee(employee.getName(), employee.getCompany(), employee.getJobTitle());
	 }
	 
	 @RequestMapping(value= { "/employees/update" }, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public @ResponseBody Employee updateEmployee(@RequestBody Employee employee) {
		 
	     return employeeDAO.updateEmployee(employee.getId(), employee.getName(), employee.getCompany(), employee.getJobTitle());
	 }
	 
	 @RequestMapping(value= { "/employees/remove/{id}" }, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	 public @ResponseBody boolean deleteEmployee(@PathVariable("id") Integer id) {
		 
	     return employeeDAO.deleteEmployee(id);
	 }
	 
}
