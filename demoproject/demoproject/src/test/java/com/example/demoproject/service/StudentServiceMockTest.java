package com.example.demoproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demoproject.exception.RecordNotFoundException;
import com.example.demoproject.model.Student;
import com.example.demoproject.repository.StudentRepo;
import com.example.demoproject.services.StudentService;
import com.example.demoproject.utils.TestUtil;

@SpringBootTest
public class StudentServiceMockTest {
	@Mock
	private StudentRepo repository;

	@InjectMocks // auto inject helloRepository
	private StudentService studentService = new StudentService();

	@BeforeEach
	void setMockOutput() {
		/*
		 * Student student = new Student(); student.setDepartment("ECE");
		 * student.setEmail("demo@demo.com"); student.setFirstName("Demo");
		 * student.setLastName("Demo"); studentService.addOrUpdateStudent(student);
		 */
		when(repository.findById(1L)).thenReturn(Optional.of(TestUtil.getInstance().getStudent()));
		List<Student> listStudents = new ArrayList<Student>();
		listStudents.add(TestUtil.getInstance().getStudent());
		when(repository.findByLastName("Demo")).thenReturn(listStudents);
	}

	@DisplayName("Student - Get By Id")
	@Test
	void testGetById() throws RecordNotFoundException {
		assertEquals("demo@demo.com", studentService.getStudentById(1L).getEmail());
	}

}
