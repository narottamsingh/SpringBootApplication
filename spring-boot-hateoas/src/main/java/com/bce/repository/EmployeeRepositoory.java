package com.bce.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bce.model.Employee;

@Repository
public interface EmployeeRepositoory extends CrudRepository<Employee, Long> {

}
