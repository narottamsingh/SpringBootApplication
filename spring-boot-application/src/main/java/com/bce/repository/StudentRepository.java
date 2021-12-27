package com.bce.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bce.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long>, PagingAndSortingRepository<Student, Long>,
		JpaSpecificationExecutor<Student> {
	// Greater Than
	public List<Student> findByDobGreaterThan(LocalDate date);

	// Less Than
	public List<Student> findByDobLessThan(LocalDate date);

	public List<Student> findByFirstname(String firstName);

	public List<Student> findByLastname(String lastName);

	// Distinct

	public List<Student> findDistinctByFirstname(String firstName);

	// Or
	public List<Student> findByFirstnameOrLastname(String firstName, String lastName);

	// Is
	public List<Student> findByFirstnameIs(String firstName);

	// Equals
	public List<Student> findByLastnameEquals(String lastName);

	// public List<Student> findByDobBetween(LocalDate date1, LocalDate date2);

	public List<Student> findByDob(LocalDate localDate);

	// Between
	public List<Student> findByDobBetween(LocalDate localDate1, LocalDate localDate2);

	// After
	public List<Student> findByDobAfter(LocalDate localDate);

	// And
	public List<Student> findByFirstnameAndLastname(String firstName, String lastName);

	// count
	public Long countByFirstname(String firstName);

	// IgnoreCase
	public List<Student> findByFirstnameIgnoreCase(String firstName);

	// Not
	public List<Student> findByFirstnameNot(String firstName);

	// OrderBy
	public List<Student> findByDobOrderByLastname(LocalDate dob);

	// StartingWith

	public List<Student> findByFirstnameStartingWith(String firstName);

	// EndingWith
	public List<Student> findByFirstnameEndingWith(String firstName);

}
