package com.bce.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bce.model.Student;
import com.bce.service.StudentService;
import com.javatpoint.exception.StudentNotFoundException;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/student")
	private long saveStudent(@RequestBody Student Student) {

		studentService.saveOrUpdate(Student);
		return Student.getId();
	}

	@GetMapping("/student")
	private List<Student> getAllStudent() {
		return studentService.getAllStudent();

	}

	@GetMapping("/student/{id}")

	private Student getStudentById(@PathVariable long id) {
		Student Student;

		try {
			Student = studentService.getStudentById(id);
		} catch (Exception e) {
			throw new StudentNotFoundException();
		}
		return Student;

	}

	@PutMapping("student/{id}/{dob}")
	private ResponseEntity<String> updateStudent(@PathVariable long id, @PathVariable String dob) {
		Student Student;

		try {
			Student = studentService.getStudentById(id);
		} catch (Exception e) {
			throw new StudentNotFoundException();
		}
		Student.setDob(LocalDate.parse(dob));

		Student.setId(id);

		studentService.saveOrUpdate(Student);

		return new ResponseEntity<>("Student updated succesfully", HttpStatus.OK);

	}

	@SuppressWarnings("unused")
	@DeleteMapping("/student/{id}")

	@ExceptionHandler
	private ResponseEntity<String> deleteStudent(@PathVariable long id) {

		Student Student;
		try {
			Student = studentService.getStudentById(id);
		} catch (Exception e) {
			throw new StudentNotFoundException();
		}
		studentService.delete(id);

		return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);

	}

	@GetMapping("/student/{pageNo}/{pageSize}")
	public List<Student> getPaginatedCountries(@PathVariable int pageNo, @PathVariable int pageSize) {

		return studentService.findPaginated(pageNo, pageSize);
	}

	@GetMapping("/studentGreater/{years}")
	public List<Student> findByDobGreaterThan(@PathVariable long years) {
		LocalDate date = LocalDate.now().minusYears(years);
		return studentService.findByDobGreaterThan(date);
	}

	@GetMapping("/studentLess/{years}")

	public List<Student> finByDobLessThan(@PathVariable long years) {
		LocalDate date = LocalDate.now().minusYears(years);

		return studentService.findByDobLessThan(date);
	}

	@GetMapping("/studentName/{firstName}/{lastName}")

	public List<Student> findByFirstnameAndLastname(@PathVariable String firstName, @PathVariable String lastName) {

		return studentService.findByFirstnameAndLastname(firstName, lastName);
	}

	@GetMapping("/studentFirstname/{firstName}")

	public List<Student> findByFirstname(@PathVariable String firstName) {
		return studentService.findByFirstname(firstName);
	}

	@GetMapping("/studentLastname/{lastName}")

	public List<Student> findByLastname(@PathVariable String lastName) {
		return studentService.findByLastname(lastName);
	}

	@GetMapping("/studentDistinct/{firstName}")

	public List<Student> findDistinctByFirstname(@PathVariable String firstName)

	{
		return studentService.findDistinctByFirstname(firstName);
	}

	@GetMapping("/studentOr/{firstName}/{lastName}")

	public List<Student> findByFirstnameOrLastname(@PathVariable String firstName, @PathVariable String lastName)

	{
		return studentService.findByFirstnameOrLastname(firstName, lastName);
	}

	@GetMapping("/studentIs/{firstName}")

	public List<Student> findByFistnameIs(@PathVariable String firstName) {
		return studentService.findByFirstnameIs(firstName);
	}

	@GetMapping("/studentEquals/{lastName}")

	public List<Student> findByLastnameEquals(@PathVariable String lastName) {
		return studentService.findByLastNameEquals(lastName);
	}

	@GetMapping("/studentDate/{localDate}")
	public List<Student> findByDob(@PathVariable String localDate) throws ParseException {

		List<Student> result = studentService.findByDob(LocalDate.parse(localDate));

		return result;

	}

	@GetMapping("/studentBetween/{start}/{end}")

	public List<Student> findByDobBetween(@PathVariable String start, @PathVariable String end) {
		List<Student> result = studentService.findByDobBetween(LocalDate.parse(start), LocalDate.parse(end));
		return result;

	}

	// @RequestMapping(value = "/custom", method = RequestMethod.POST)
	@GetMapping("/studentAfter/{after}")

	public List<Student> findByDobAfter(@PathVariable String after) {

		return studentService.findByDobAfter(LocalDate.parse(after));

	}

	@GetMapping("/studentcount/{firstName}")
	public Long countByFirstName(@PathVariable String firstName) {
		return studentService.countByFirstname(firstName);
	}

	@GetMapping("/studentIgnoreCase/{firstName}")

	public List<Student> findByFirstNameIgnoreCase(@PathVariable String firstName) {
		return studentService.findByFirstnameIgnoreCase(firstName);
	}

	@GetMapping("/studentNot/{firstName}")
	public List<Student> findByFirstnameNot(@PathVariable String firstName) {
		return studentService.findByFirstnameNot(firstName);
	}

	@GetMapping("/studentOrderBy/{dob}")
	public List<Student> findByDobOrderByLastname(@PathVariable String dob) {
		return studentService.findByDobOrderByLastname(LocalDate.parse(dob));
	}

	@GetMapping("/studentStatingWith/{firstName}")
	public List<Student> findByFirstnameStartingWith(@PathVariable String firstName) {
		return studentService.findByFirstnameStartingWith(firstName);
	}

	@GetMapping("/studentEndingWith/{firstName}")
	public List<Student> findByFirstnameEndingWith(@PathVariable String firstName) {
		return studentService.findByFirstnameEndingWith(firstName);
	}

	@PostMapping("/studentSpec")
	public List<Student> findBySpec(@RequestBody Student student) {

		return studentService.findByAdvanceSearch(student);
	}
}
