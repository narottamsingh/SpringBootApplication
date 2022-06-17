package com.bce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bce.model.Employee;
import com.bce.service.EmployeeService;

import reactor.core.publisher.Sinks;

@Service
public class ServiceImpl implements EmployeeService {

	@Autowired
	private Sinks.Many<Employee> employeeStream;

	@Override
	public void processRecords(Employee employee) {
		Sinks.EmitResult result = employeeStream.tryEmitNext(employee);
		if (result.isFailure()) {
			return;
		}

	}

}
