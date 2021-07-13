package com.example.demoproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demoproject.exception.RecordNotFoundException;
import com.example.demoproject.frontmodel.StudentWrapper;
import com.example.demoproject.model.Student;
import com.example.demoproject.services.StudentService;
import com.example.demoproject.utils.TestUtil;

@SpringBootTest
public class StudentServiceTest {
	@Autowired
	StudentService StudentRepo;

	@BeforeEach
	public void setUp() {
		Student student = new Student();
		student.setDepartment("ECE");
		student.setEmail("demo@demo.com");
		student.setFirstName("Demo");
		student.setLastName("Demo");
		StudentRepo.saveStudent(student);
	}

	@DisplayName("Student - Get By Id")
	@Test
	void testGetStudentById() throws RecordNotFoundException {
		assertEquals("demo@demo.com", StudentRepo.getStudentById(1L).getEmail());
	}

	@DisplayName("Student - Save")
	@Test
	void testSave() {
		assertEquals("CSE", StudentRepo.saveStudent(TestUtil.getInstance().getStudent()).getDepartment());
	}

	@DisplayName("Student - Update")
	@Test
	void testUpdate() throws RecordNotFoundException {
		StudentWrapper updateStudent = StudentRepo.getStudentById(1L);
		assertEquals("ECE", updateStudent.getDepartment());
		updateStudent.setDepartment("CSE");
		assertEquals("CSE", StudentRepo.updateTheStudentDetails(updateStudent).getDepartment());
	}

}
