package com.example.demoproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demoproject.model.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {
	List<Student> findByLastName(String lastname);
}
