package com.airtel.mongo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.airtel.mongo.models.Employee;
import com.airtel.mongo.service.EmployeeService;

//import net.minidev.json.JSONObject;


//Controller for actions taken by Admin : Add, Update and Delete an Employee Details
@RestController
public class EmployeeActionController {
	
	@Autowired
	EmployeeService emplService;

	// View Details of all the Employees in the organisation
	@RequestMapping(value = "/view", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Employee>> list(HttpSession session) {

		List<Employee> empList = emplService.listEmployee();
		
		if(empList == null) {
			System.out.println("No Employee in the database");
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}
	
	
	//Find user by id
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Employee> findEmployee(@PathVariable("id") String id) {
		
		Employee employee = emplService.findEmployeeById(id);
		
		if (employee == null) {
			System.out.println("Employee with id " + id + " not found");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		
		

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	
	//Add details of a new Employee 
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody Employee employee, UriComponentsBuilder uriBuilder) {
		
		emplService.add(employee);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri());
		System.out.println(headers);
		return new ResponseEntity<Void>(headers, HttpStatus.OK);

	}
	

	// Update the Existing Employee Details
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") String id,
			@RequestBody Employee employee) {
		
		if(emplService.findEmployeeById(id) == null) {
			System.out.println("Employee with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		employee.setId(id);
		emplService.update(employee);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


	// Delete Details of an Employee who has left the organisation
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {

		Employee employee = emplService.findEmployeeById(id);
		
		if(employee == null) {
			System.out.println("Employee with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		emplService.delete(employee);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
