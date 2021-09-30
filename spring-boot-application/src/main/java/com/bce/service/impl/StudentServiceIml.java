package com.bce.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bce.controller.LoggingController;
import com.bce.model.Student;
import com.bce.repository.StudentRepository;
import com.bce.service.StudentService;

@Service
public class StudentServiceIml implements StudentService {
	@Autowired
	StudentRepository studentRepository;

	Logger logger = LoggerFactory.getLogger(LoggingController.class);

	@Override
	public void saveOrUpdate(Student Student) {

		logger.info("Student Succesfully created");
		studentRepository.save(Student);
	}

	@Override
	public List<Student> getAllStudent() {
		logger.info("All student");
		List<Student> Students = (List<Student>) studentRepository.findAll();

		return Students;
	}

	@Override
	public Student getStudentById(long id) {

		return studentRepository.findById(id).get();
	}

	@Override
	public void delete(long id) {

		studentRepository.deleteById(id);
	}

	@Override
	public List<Student> findPaginated(int pageNo, int pageSize) {

		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Student> pagedResult = studentRepository.findAll(paging);

		return pagedResult.toList();
	}

	@Override
	public List<Student> findByDobGreaterThan(LocalDate date)

	{
		List<Student> Students = studentRepository.findByDobGreaterThan(date);
		return Students;

	}

	@Override
	public List<Student> findByDobLessThan(LocalDate date) {

		List<Student> Students = studentRepository.findByDobLessThan(date);
		return Students;

	}

	@Override
	public List<Student> findByFirstnameAndLastname(String firstName, String lastName) {
		List<Student> Students = studentRepository.findByFirstnameAndLastname(firstName, lastName);

		return Students;
	}

	@Override
	public List<Student> findByFirstname(String firstName) {
		List<Student> Students = studentRepository.findByFirstname(firstName);

		return Students;
	}

	@Override
	public List<Student> findByLastname(String lastName) {

		List<Student> Students = studentRepository.findByLastname(lastName);

		return Students;
	}

	@Override
	public List<Student> findDistinctByFirstname(String firstName) {
		return studentRepository.findDistinctByFirstname(firstName);

	}

	@Override
	public List<Student> findByFirstnameOrLastname(String firstName, String lastName) {
		return studentRepository.findByFirstnameOrLastname(firstName, lastName);
	}

	@Override
	public List<Student> findByFirstnameIs(String firstName) {
		return studentRepository.findByFirstnameIs(firstName);
	}

	@Override
	public List<Student> findByLastNameEquals(String lastName) {
		return studentRepository.findByLastnameEquals(lastName);
	}

	@Override
	public List<Student> findByDobBetween(LocalDate date1, LocalDate date2) {
		return studentRepository.findByDobBetween(date1, date2);
	}

	@Override
	public List<Student> findByDob(LocalDate localDate) {

		return studentRepository.findByDob(localDate);
	}

	@Override
	public List<Student> findByDobAfter(LocalDate localDate) {
		return studentRepository.findByDobAfter(localDate);
	}

	@Override
	public Long countByFirstname(String firstName) {
		return studentRepository.countByFirstname(firstName);
	}

	@Override
	public List<Student> findByFirstnameIgnoreCase(String firstName) {
		return studentRepository.findByFirstnameIgnoreCase(firstName);
	}

	@Override
	public List<Student> findByFirstnameNot(String firstName) {
		return studentRepository.findByFirstnameNot(firstName);
	}

	@Override
	public List<Student> findByDobOrderByLastname(LocalDate dob) {
		return studentRepository.findByDobOrderByLastname(dob);
	}

	@Override
	public List<Student> findByFirstnameStartingWith(String firstName) {
		return studentRepository.findByFirstnameStartingWith(firstName);
	}

	@Override
	public List<Student> findByFirstnameEndingWith(String firstName) {

		return studentRepository.findByFirstnameEndingWith(firstName);
	}

	@Override
	public List<Student> findByAdvanceSearch(Student student) {
		return findAll(new Specification<Student>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				// 1. like
				// if (!student.getFirstname().equals("")) {
				// predicates.add(
				// criteriaBuilder.and(criteriaBuilder.like(root.get("firstname"),
				// student.getFirstname())));
				// }

				// 2. between
				// if (!student.getDob().equals("")) {
				// {
				// predicates.add(criteriaBuilder.between(root.get("dob"), student.getDob(),
				// LocalDate.now()));

				// predicates.add(criteriaBuilder.between(root.get("dob"),
				// LocalDate.now().minusYears(36),
				// LocalDate.now()));
				// }

				// }

				// 3. equal
				// if (!student.getFirstname().equals(""))
				// predicates.add(criteriaBuilder.equal(root.get("firstname"),
				// student.getFirstname()));

				List<String> list = new ArrayList<String>();
				list.add("Rahul");
				list.add("Shyam");
				// 4. in
				// predicates.add(criteriaBuilder.in(root.get("firstname")).value(list));

				// 5. equal
				// if (!student.getFirstname().equals(""))
				// predicates.add(criteriaBuilder.equal(root.get("firstname"),
				// student.getFirstname()));

				// 6. greaterThan
				// predicates.add(criteriaBuilder.greaterThan(root.get("dob"),
				// student.getDob()));

				// 7.greaterThanOrEqualTo
				// predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dob"),
				// student.getDob()));

				// 8.lessThan
				// predicates.add(criteriaBuilder.lessThan(root.get("dob"), student.getDob()));

				// 9. ge
				// predicates.add(criteriaBuilder.ge(root.get("id"), (Number) student.getId()));

				predicates.add(criteriaBuilder.gt(root.get("id"), (Number) student.getId()));

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

//		}, new Specification<Student>() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//				List<Predicate> predicates = new ArrayList<Predicate>();
//
//				if (!student.getLastname().equals("")) {
//					predicates.add(
//							criteriaBuilder.and(criteriaBuilder.like(root.get("lastname"), student.getLastname())));
//				}
//
//				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//
//			}
//		});

	private List<Student> findAll(Specification<Student> spec1) {
		return studentRepository.findAll(spec1);

	}

}
