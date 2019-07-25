package com.airtel.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtel.mongo.dao.EmployeeDao;
import com.airtel.mongo.models.Employee;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// Bean
	@Autowired
	EmployeeDao emplDao;

	public List<Employee> listEmployee() {
		return emplDao.listEmployee();
	}

	public void add(Employee empl) {
		emplDao.add(empl);
	}

	public void update(Employee empl) {
		emplDao.update(empl);
	}

	public void delete(Employee empl) {
		emplDao.delete(empl);
	}

	public Employee findEmployeeById(String id) {
		return emplDao.findEmployeeById(id);
	}
}