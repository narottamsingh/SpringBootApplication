package com.bce.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bce.model.Student;

@Service
public interface StudentService {

	void saveOrUpdate(Student student);

	List<Student> getAllStudent();

	Student getStudentById(long id);

	void delete(long id);

	List<Student> findPaginated(int pageNo, int pageSize);

	List<Student> findByDobGreaterThan(LocalDate date);

	List<Student> findByDobLessThan(LocalDate date);

	List<Student> findByFirstnameAndLastname(String firstName, String lastName);

	List<Student> findByFirstname(String firstName);

	List<Student> findByLastname(String lastName);

	List<Student> findDistinctByFirstname(String firstName);

	List<Student> findByFirstnameOrLastname(String firstName, String lastName);

	List<Student> findByFirstnameIs(String firstName);

	List<Student> findByLastNameEquals(String lastName);

	List<Student> findByDob(LocalDate parse);

	List<Student> findByDobBetween(LocalDate parse, LocalDate parse2);

	List<Student> findByDobAfter(LocalDate parse);

	Long countByFirstname(String firstName);

	List<Student> findByFirstnameIgnoreCase(String firstName);

	List<Student> findByFirstnameNot(String firstName);

	List<Student> findByDobOrderByLastname(LocalDate parse);

	List<Student> findByFirstnameStartingWith(String firstName);

	List<Student> findByFirstnameEndingWith(String firstName);

	List<Student> findByAdvanceSearch(Student student);

}
