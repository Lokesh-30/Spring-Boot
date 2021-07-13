package com.example.demoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demoproject.exception.RecordNotFoundException;
import com.example.demoproject.frontmodel.StudentWrapper;
import com.example.demoproject.model.Student;
import com.example.demoproject.services.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentSerivce;

	@PostMapping("/saveStudent")
	public ResponseEntity<Student> saveStudent(@RequestBody Student studentWrapper) {
		studentWrapper = studentSerivce.saveStudent(studentWrapper);
		return ResponseEntity.ok(studentWrapper);
	}

	@GetMapping("/getStudent/{id}")
	public ResponseEntity<StudentWrapper> getStudentById(@PathVariable Long id) throws RecordNotFoundException {
		StudentWrapper studentWrapper = studentSerivce.getStudentById(id);
		return ResponseEntity.ok(studentWrapper);
	}

	@PutMapping("/updateStudent")
	public ResponseEntity<StudentWrapper> updateStudent(@RequestBody StudentWrapper studentWrapper)
			throws RecordNotFoundException {
		studentWrapper = studentSerivce.updateTheStudentDetails(studentWrapper);
		return ResponseEntity.ok(studentWrapper);
	}

	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<StudentWrapper> deleteById(@PathVariable Long id) throws RecordNotFoundException {
		studentSerivce.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
