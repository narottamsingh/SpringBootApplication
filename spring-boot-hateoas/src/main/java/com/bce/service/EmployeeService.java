package com.bce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bce.model.Employee;

@Service
public interface EmployeeService {

	List<Employee> getAllEmployee();

	Employee getEmployeeById(long id);

}
