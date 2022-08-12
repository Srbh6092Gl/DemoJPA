package com.globallogic.demojpa.controller;

import java.util.List;
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
import com.globallogic.demojpa.repository.DepartmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	DepartmentRepository deptRepo;
	
	//GET requests
	
	//GET request for fetching all department
	@GetMapping
	public List<Department> getAllDepartments(){
		return deptRepo.findAll();
	}
	
	//GET request for fetching a department by ID
	@GetMapping("/{id}")
	public Department getDepartmentById(@PathVariable int id) throws Exception {
		Optional<Department> response = deptRepo.findById(id);
		if(response.isEmpty())
			throw new Exception("Student not found");
		return response.get();
	}
	
	
	//POST requests
	
	//POST request to add a department
	@PostMapping
	public Department addDepartment(@RequestBody Department department) {
		return deptRepo.save(department);
	}
	
	
	//DELETE requests
	
	//DELETE request to delete a department by id
	@DeleteMapping("/{id}")
	public String deleteDepartmentById(@PathVariable int id) {
		deptRepo.deleteById(id);
		return "Delete ID="+id+": SUCCESS";
	}
	
	//PUT requests
	
	//PUT request to update a department
	//If any field is missing, it will take default value for the respective field
	@PutMapping
	public Department updateDepartment(@RequestBody Department department) {
		return deptRepo.save(department);
	}
	
	//PUT request to update a department by id
	@PutMapping("/{id}")
	public Department updateDepartmentById(@RequestBody Department changes, @PathVariable int id) throws Exception {
		Optional<Department> response = deptRepo.findById(id);
		if(response.isEmpty())
			throw new Exception("Department not found");
		Department department=response.get();
		if(changes.getName()!=null && !changes.getName().equalsIgnoreCase(""))
			department.setName(changes.getName());
		if(changes.getLocation()!=null && !changes.getLocation().equalsIgnoreCase(""))
			department.setLocation(changes.getLocation());
		return deptRepo.save(department);
	}
	
}
