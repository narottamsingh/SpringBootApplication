package com.bce.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bce.model.Employee;
import com.bce.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employee")
	public List<Employee> findAll() {
		return employeeService.getAllEmployee();
	}

	@GetMapping("/employee/{id}")
	public EntityModel<Employee> findEmployeeById(@PathVariable long id) {

		EntityModel<Employee> resource = EntityModel.of(employeeService.getEmployeeById(id));

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());

		resource.add(linkTo.withRel("all-students"));

		return resource;

	}

}
