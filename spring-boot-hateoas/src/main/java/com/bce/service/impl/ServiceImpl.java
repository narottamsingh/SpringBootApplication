package com.bce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bce.model.Employee;
import com.bce.repository.EmployeeRepositoory;
import com.bce.service.EmployeeService;

@Service
public class ServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepositoory employeeRepositoory;


	@Override
	public List<Employee> getAllEmployee() {
		return (List<Employee>) employeeRepositoory.findAll();

	}

	@Override
	public Employee getEmployeeById(long id) {
		Employee employee = employeeRepositoory.findById(id).get();
		return employee;
	}

}
