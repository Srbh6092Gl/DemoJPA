package com.globallogic.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globallogic.demojpa.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
}
