package com.haile.apps.employees.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.haile.apps.employees.dao.EmployeeDAO;
import com.haile.apps.employees.entity.Employee;

@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeesList() {
		Session session = this.sessionFactory.getCurrentSession();

		List<Employee> employees = (List<Employee>) session.createQuery("from Employee").list();

		return employees;
	}

	public Employee getEmployeeById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();

		Employee employee = (Employee) session.get(Employee.class, id);

		return employee;
	}

	public Integer getHighestID() {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "Select max(d.id) from Employee d ";
		Query query = session.createQuery(sql);
		Integer maxDeptId = (Integer) query.uniqueResult();
		if (maxDeptId == null) {
			return 0;
		}
		return maxDeptId;
	}

	public Integer createEmployee(String name, String company, String jobTitle) {
		int id = getHighestID() + 1;
		Employee employee = new Employee();
		employee.setId(id);
		employee.setName(name);
		employee.setCompany(company);
		employee.setJobTitle(jobTitle);
		Session session = this.sessionFactory.getCurrentSession();
		Integer newId = (Integer) session.save(employee);
		return newId;
	}

	public Employee updateEmployee(int id, String name, String company, String jobTitle) {//??????
		Session session = this.sessionFactory.getCurrentSession();
		Employee employee = new Employee(id, name, company, jobTitle);
		session.saveOrUpdate(employee);
		return employee;
	}
	
	public boolean deleteEmployee(Integer id){
		Session session = this.sessionFactory.getCurrentSession();
		Employee employee = null;
	    employee = (Employee)session.load(Employee.class,id);
	    if (employee != null) {
	    	session.delete(employee);
	    	//This makes the pending delete to be done
	    	session.flush();
	    	return true;
	    }	    
	    return false;
	}
}
