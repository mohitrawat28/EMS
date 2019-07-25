package com.airtel.mongo.service;

import java.util.List;
import com.airtel.mongo.models.Employee;

public interface EmployeeService {

    public List<Employee> listEmployee();

    public void add(Employee empl);

    public void update(Employee empl);

    public void delete(Employee empl);

    public Employee findEmployeeById(String id);
}