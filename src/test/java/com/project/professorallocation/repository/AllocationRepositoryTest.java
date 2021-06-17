package com.project.professorallocation.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.entity.Allocation;
import com.project.professorallocation.entity.Course;
import com.project.professorallocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {
	
	@Autowired
	private AllocationRepository allocationRepository;
	
	@Test
	void test1()
	{
		//Create (CRUD)
		Allocation aloc = new Allocation();
		allocationRepository.save(aloc);
	}
	
	@Test
	void test2()
	{
		//Read by Id
		
		Long id = 3L; //selecione o id desejado
		Optional<Allocation> optional = allocationRepository.findById(id);
		Allocation a = optional.orElse(new Allocation());
		
		System.out.println(a.toString());
	}
	
	@Test
	void testByProfessor()
	{
		Professor professor = new Professor(); //implementa com .getName
		List<Allocation> listProf = allocationRepository.findByAllocationLikeProfessor(professor);
		
		System.out.println(listProf.toString());
	}
	
	@Test
	void testByCourse()
	{
		Course course = new Course(); //implementa com .getName
		List<Allocation> listCourse = allocationRepository.findByAllocationLikeCourse(course);
		
		System.out.println(listCourse.toString());
	}
	
	@Test
	void test3()
	{
		//FindAll Read (CRUD)
		List<Allocation> allocationList = allocationRepository.findAll();
		
		System.out.println(allocationList.toString());
	}
	
	@Test
	void test4(Long id)
	{
		//Update (CRUD)
		if(allocationRepository.existsById(id))
		{
			Optional<Allocation> optional = allocationRepository.findById(id);
			Allocation a = optional.get();
			//User updates whatever he wants -> .setName/.setCPF/.setDepto
			allocationRepository.save(a);
		}
	}
	
	@Test
	void test5(Long id)
	{
		//Delete (CRUD)
		allocationRepository.deleteById(id);
	}
	
	@Test
	void test6()
	{
		//Delete all in batch(CRUD)
		allocationRepository.deleteAllInBatch();
	}	
}
