
package com.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.demo.entity.Student;
import com.demo.model.Student_DTO;
import com.demo.service.StudentService;
import com.demo.util.Converter;

import jakarta.validation.Valid;

@RestController  // controller + ResponseBody

public class Controller {

	// connecting from controller(this file) to service(StudentService)  
	// validation is done for create and update only using @valid annotation
			@Autowired
			StudentService service;
			
			@Autowired
			Converter con;
			
			@PostMapping("/api/createStudent") 
			// @RequestBody annotation converts the json to object and store into database
//			Student_DTO createStudent(@Valid @RequestBody Student_DTO sd) {
//				Student s = con.convertToStudentEntity(sd);
//				return service.createStudent(s);
//			}
			ResponseEntity<?> createStudent(@Validated @RequestBody Student_DTO sd, BindingResult bindingResult) {
		        if (bindingResult.hasErrors()) {
		            // Validation errors occurred, handle them here
		        	List<String> errors = bindingResult.getAllErrors()
		            .stream()
		            .map(DefaultMessageSourceResolvable::getDefaultMessage)
		            .collect(Collectors.toList());
		        	 return ResponseEntity.badRequest().body("Validation failed: " + errors);
		        	 } else {
		            // Proceed with creating the user since validation passed
		            // Your logic here to create the user
		            Student s = con.convertToStudentEntity(sd);
		            Student_DTO createdStudent = service.createStudent(s);
		            if (createdStudent != null) {
		                return ResponseEntity.ok(createdStudent); // Return the created student DTO
		            } else {
		                // Handle the case where the student creation failed
		                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create student");
		            }
		        }
		    }
			
			@GetMapping("/getStudents")
			public List<Student_DTO> getStudents() {
				
				return service.getStudents();
			}
			
			@GetMapping("/getStudentById/{id}")
			// Path Variable bind the URI parameter to the method parameter.
			Student_DTO getStudentById(@PathVariable int id) {
				return service.getStudentById(id);
			}
			
			@PutMapping("/api/updateStudent/{id}")
//			public Student_DTO updateStudent(@Valid @PathVariable int id,@RequestBody Student st) {
//				return service.updateStudent(id, st);
//			}
			ResponseEntity<?> updateStudent(@Validated @PathVariable int id, @Validated @RequestBody Student_DTO updatedStudent,
					BindingResult bindingResult) {

				if (bindingResult.hasErrors()) {
					System.out.println("errror");
					List<String> errors = bindingResult.getAllErrors().stream()
							.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
					return ResponseEntity.badRequest().body("Validation failed: " + errors);
				} else {
					System.out.println("no  errror");

					Student s = con.convertToStudentEntity(updatedStudent);
					Student_DTO result = service.updateStudent(id, s);
					if (result != null) {
						return ResponseEntity.ok(result);
					} else {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update student");
					}
				}
				}
			public Student_DTO updateStudent(@Validated @PathVariable int id, @RequestBody Student updatedStudent) {    
		    	return  service.updateStudent(id, updatedStudent);
			}
			
			@DeleteMapping("/deleteById/{id}")
			public String deleteStudentById(@PathVariable int id) {
				return service.deleteStudentById(id);
			}
}


	
	 // API Layer -- first point of interaction
		
		
		

