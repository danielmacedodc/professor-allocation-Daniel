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
		Long id = 3L;
		List<Allocation> listProf = allocationRepository.findByProfessorId(id);
		
		System.out.println(listProf.toString());
	}
	
	@Test
	void testByCourse()
	{
		Long id = 3L;
		List<Allocation> listCourse = allocationRepository.findByCourseId(id);
		
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
	void test4()
	{
		//Update (CRUD)
		Long id = 3L;
		
		if(allocationRepository.existsById(id))
		{
			Optional<Allocation> optional = allocationRepository.findById(id);
			Allocation a = optional.get();
			//User updates whatever he wants -> .setName/.setCPF/.setDepto
			allocationRepository.save(a);
		}
	}
	
	@Test
	void test5()
	{
		//Delete (CRUD)
		Long id = 2L;
		
		allocationRepository.deleteById(id);
	}
	
	@Test
	void test6()
	{
		//Delete all in batch(CRUD)
		allocationRepository.deleteAllInBatch();
	}	
}
