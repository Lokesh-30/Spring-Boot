package com.example.demoproject.utils;

import com.example.demoproject.model.Student;

public final class TestUtil {

	private static TestUtil testUtil = null;

	public static TestUtil getInstance() {
		if (testUtil == null)
			testUtil = new TestUtil();
		return testUtil;
	}

	public Student getStudent() {
		Student student = new Student();
		student.setDepartment("CSE");
		student.setEmail("test@test.com");
		student.setFirstName("Test");
		student.setLastName("Test");
		return student;
	}

}
