package com.airtel.mongo.dao;

import com.airtel.mongo.models.Employee;

import java.util.List;

public interface EmployeeDao {

    public List<Employee> listEmployee();

    public void add(Employee empl);

    public void update(Employee empl);

    public void delete(Employee empl);

    public Employee findEmployeeById(String id);
}