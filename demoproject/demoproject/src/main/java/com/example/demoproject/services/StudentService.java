package com.example.demoproject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoproject.exception.RecordNotFoundException;
import com.example.demoproject.frontmodel.StudentWrapper;
import com.example.demoproject.model.Student;
import com.example.demoproject.repository.StudentRepo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo repository;

	public Student saveStudent(Student inputStudent) {
		Student stu = new Student();
		// stu.setId(inputStudent.getId());
		stu.setFirstName(inputStudent.getFirstName());
		stu.setLastName(inputStudent.getLastName());
		stu.setDepartment(inputStudent.getDepartment());
		stu.setEmail(inputStudent.getEmail());
		stu = repository.save(stu);
		inputStudent.setId(stu.getId());
		inputStudent.setFirstName(stu.getFirstName());
		inputStudent.setLastName(stu.getLastName());
		inputStudent.setDepartment(stu.getDepartment());
		inputStudent.setEmail(stu.getEmail());
		return inputStudent;
	}

	public StudentWrapper getStudentById(Long id) throws RecordNotFoundException {
		StudentWrapper studentWrapperOutput = null;
		Optional<Student> studentOptionalData = repository.findById(id);

		if (studentOptionalData.isPresent()) {
			studentWrapperOutput = new StudentWrapper();
			Student student = studentOptionalData.get();
			studentWrapperOutput.setId(student.getId());
			studentWrapperOutput.setFirstName(student.getFirstName());
			studentWrapperOutput.setLastName(student.getLastName());
			studentWrapperOutput.setDepartment(student.getDepartment());
			studentWrapperOutput.setEmail(student.getEmail());
		} else {
			throw new RecordNotFoundException("Student record not found");
		}
		return studentWrapperOutput;
	}

	public StudentWrapper updateTheStudentDetails(StudentWrapper inputStudent) throws RecordNotFoundException {
		Optional<Student> studentOptionalData = repository.findById(inputStudent.getId());

		if (studentOptionalData.isPresent()) {
			Student student = studentOptionalData.get();
			// update name
			student.setFirstName(inputStudent.getFirstName());
			student.setLastName(inputStudent.getLastName());
			student.setDepartment(inputStudent.getDepartment());
			student.setEmail(inputStudent.getEmail());
			repository.save(student);

			// populated the updated details in the output object
			StudentWrapper studentWrapper = new StudentWrapper();
			studentWrapper.setId(student.getId());
			studentWrapper.setFirstName(student.getFirstName());
			studentWrapper.setLastName(student.getLastName());
			studentWrapper.setDepartment(student.getDepartment());
			studentWrapper.setEmail(student.getEmail());
			return studentWrapper;
		} else {
			throw new RecordNotFoundException("Student record not found");
		}
	}

	public void deleteById(Long id) throws RecordNotFoundException {
		Optional<Student> studentOptionalData = repository.findById(id);

		if (studentOptionalData.isPresent()) {
			Student student = studentOptionalData.get();
			repository.delete(student);
		} else {
			throw new RecordNotFoundException("Student record not found");
		}
	}

}
