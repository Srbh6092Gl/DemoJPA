package com.globallogic.demojpa.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.demojpa.entity.Department;
import com.globallogic.demojpa.entity.Student;
import com.globallogic.demojpa.repository.DepartmentRepository;
import com.globallogic.demojpa.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentRepository stuRepo;
	
	@Autowired
	DepartmentRepository deptRepo;
	
	//GET requests
	
	//GET request for fetching all students
	@GetMapping
	public List<Student> getAllStudents(){
		return stuRepo.findAll();
	}
	
	//GET request for fetching a student by ID
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable int id) throws Exception {
		Optional<Student> response = stuRepo.findById(id);
		if(response.isEmpty())
			throw new Exception("Student not found");
		return response.get();
	}
	
	
	//POST requests
	
	//POST request to add a student
	@PostMapping
	public Student addStudent(@RequestBody Student student) {
		Optional<Department> deptById = deptRepo.findById(student.getDepartment().getId());
		if(deptById.isPresent()) {
			Department department = deptById.get();
			student.setDepartment(department);
		}
		return stuRepo.save(student);
	}
	
	
	//DELETE requests
	
	//DELETE request to delete a student by id
	@DeleteMapping("/{id}")
	public String deleteStudentById(@PathVariable int id) {
		stuRepo.deleteById(id);
		return "Delete ID="+id+": SUCCESS";
	}
	
	//PUT requests
	
	//PUT request to update a student
	//If any field is missing, it will take default value for the respective field
	@PutMapping
	public Student updateStudent(@RequestBody Student student) {
		return stuRepo.save(student);
	}
	
	//PUT request to update a student by id
	@PutMapping("/{id}")
	public Student updateStudentById(@RequestBody Student changes, @PathVariable int id) throws Exception {
		Optional<Student> response = stuRepo.findById(id);
		if(response.isEmpty())
			throw new Exception("Student not found");
		Student student=response.get();
		if(changes.getName()!=null && !changes.getName().equalsIgnoreCase(""))
			student.setName(changes.getName());
		if(Objects.nonNull(changes.getAdmNo()))
			student.setAdmNo(changes.getAdmNo());
		return stuRepo.save(student);
	}
	
}