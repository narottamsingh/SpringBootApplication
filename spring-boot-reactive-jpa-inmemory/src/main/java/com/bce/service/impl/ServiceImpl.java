package com.bce.service.impl;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bce.model.Employee;
import com.bce.repository.EmployeeRepository;
import com.bce.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
public class ServiceImpl implements EmployeeService {

	@Autowired
	private Sinks.Many<Employee> employeeStream;

	@Autowired
	private EmployeeRepository eR;

	@Override
	public void processRecords(Employee employee) {
		employee.setDate(LocalDate.now());

		employee = saveEmployee(employee);
		if (employee != null) {

			Sinks.EmitResult result = employeeStream.tryEmitNext(employee);

			if (result.isFailure()) {
				return;
			} else {
				System.out.println("Data not persist");
			}
		}
	}

	@Override
	public Employee saveEmployee(Employee employee) {

		return eR.save(employee);
	}

	@Override
	public Flux<Employee> findTodayEmployee() {
		Flux<Employee> defer = Flux.defer(() -> Flux.fromIterable(this.eR.findAll()));
		return defer;

	}

}
