package com.project.professorallocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professorallocation.entity.Allocation;
import com.project.professorallocation.entity.Course;
import com.project.professorallocation.entity.Professor;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long>{

	List<Allocation> findByAllocationLikeProfessor(Professor professor);
	
	List<Allocation> findByAllocationLikeCourse(Course course);
}
