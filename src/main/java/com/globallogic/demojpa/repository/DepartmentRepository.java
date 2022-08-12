package com.globallogic.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globallogic.demojpa.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}