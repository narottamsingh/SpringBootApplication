package com.bce.controller;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bce.model.Employee;
import com.bce.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	private Sinks.Many<Employee> employeeStream;
	final AtomicLong counter = new AtomicLong();

	@PostMapping("/employee")
	public void saveEmployee(@RequestBody Employee employee) {
		employeeService.processRecords(employee);
	}
	
	@GetMapping(value = "/suscribers")
	public Mono<Long> getSubscriber() {
		return employeeStream.asFlux().count();
	}

	@GetMapping(value = "/employeeStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<Employee>> employeeStream() {
		return employeeStream.asFlux().map(e -> ServerSentEvent.builder(e).build());
	}

	@GetMapping(value = "/employeeStream/{dept}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<Employee>> getLeadData(@PathVariable String dept) {
		
		Predicate<Employee> departmentEquals = e -> e.getDepartment().equals(dept);
		Predicate<Employee> nameEquals = e -> e.getFirstname().equals("Narottam");
		 
	
		return employeeStream.asFlux().filter(departmentEquals.and(nameEquals))
				.map(data -> ServerSentEvent.builder(data).build());
	}

}
