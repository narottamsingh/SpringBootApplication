package com.bce.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bce.model.Student;

@RestController
public class StudentController {

	Set<Student> students = new LinkedHashSet<>();

	@PostMapping("/student")
	private Set<Student> saveStudent(@RequestBody Student student) {
		students.add(student);
		return students;
	}

	@GetMapping("/student")
	private Set<Student> getAllStudent() {
		return students;
	}
}