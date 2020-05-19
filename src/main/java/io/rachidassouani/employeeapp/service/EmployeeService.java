package io.rachidassouani.employeeapp.service;

import io.rachidassouani.employeeapp.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
}
