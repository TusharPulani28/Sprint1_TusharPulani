    package com.demo.serviceImpl;
	import java.util.*;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	import com.demo.entity.Student;
	import com.demo.model.Student_DTO;
	import com.demo.repository.StudentRepo;
	import com.demo.service.StudentService;
	import com.demo.util.Converter;

	@Service  // internally we need to tell Springboot that any service layer method will be called , service impl class will act as a sevice.
	public class StudentServiceImpl implements StudentService {

		@Autowired     // connecting from service(this file) to repository(StudentService) 
		StudentRepo r; // to store the data in a database 
		
		@Autowired
		Converter c; // injection
		
		@Override
		public Student_DTO createStudent(Student st) {	
			Student st1 = r.save(st);
			return c.convertToStudentDto(st1);
		}

		@Override
		public List<Student_DTO> getStudents() {
			
			List<Student> st = r.findAll();
			List<Student_DTO> dtoList = new ArrayList();
			for(Student s : st)
			{
			dtoList.add(c.convertToStudentDto(s));
			}
			return dtoList;
		}

		@Override
		public Student_DTO getStudentById(int id) {
		
			
			Student s = r.findById(id).get();
			return c.convertToStudentDto(s);
		}

		@Override
		public Student_DTO updateStudent(int id, Student s) {
			Student s1 = r.findById(id).get();
			s1.setName(s.getName());
			s1.setPhone_No(s.getPhone_No());
			s1.setAddress(s.getAddress());
			Student ss = r.save(s1);
			return c.convertToStudentDto(ss);
		}

		@Override
		public String deleteStudentById(int id) {
			
			r.deleteById(id);
			return "Deleted Successfully";
		}

				
	}


