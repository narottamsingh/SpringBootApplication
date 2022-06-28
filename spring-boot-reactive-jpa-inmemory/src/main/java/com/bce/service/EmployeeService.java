package com.bce.service;

import com.bce.model.Employee;

import reactor.core.publisher.Flux;

public interface EmployeeService {

	void processRecords(Employee employee);

	Employee saveEmployee(Employee employee);

	Flux<Employee> findTodayEmployee();
}
