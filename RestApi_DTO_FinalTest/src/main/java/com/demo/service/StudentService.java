package com.demo.service;

import java.util.List;

import com.demo.entity.Student;
import com.demo.model.Student_DTO;

public interface StudentService {
    // return replica of entity
	Student_DTO createStudent(Student st);
	// After creating student in database it will return a student object
	
	List<Student_DTO> getStudents();
	
	Student_DTO getStudentById(int id);
	
	Student_DTO updateStudent(int id , Student s);
	
	String deleteStudentById(int id);
	
	
}
