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

import com.project.professorallocation.entity.Professor;

//Para teste é sempre classe
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	//todo método de teste é void
	
	@Test
	void test1()
	{
		//Create (CRUD)
		Professor professor = new Professor();
		professorRepository.save(professor);
	}
	
	@Test
	void test2()
	{
		//Read by Id
		
		Long id = 1L; //selecione o id desejado
		Optional<Professor> optional = professorRepository.findById(id);
		Professor p = optional.orElse(new Professor());
		
		System.out.println(p.toString());
	}
	
	@Test
	void testByName()
	{
		String name = "Daniel";
		List<Professor> list = professorRepository.findByNameContainingIgnoreCase(name);
		
		System.out.println(list.toString());
	}
	
	@Test
	void test3()
	{
		//FindAll Read (CRUD)
		List<Professor> professorList = professorRepository.findAll();
		
		System.out.println(professorList.toString());
	}
	
	@Test
	void test4(Long id)
	{
		//Update (CRUD)
		if(professorRepository.existsById(id))
		{
			Optional<Professor> optional = professorRepository.findById(id);
			Professor p = optional.get();
			//User updates whatever he wants -> .setName/.setCPF/.setDepto
			professorRepository.save(p);
		}
	}
	
	@Test
	void test5(Long id)
	{
		//Delete (CRUD)
		professorRepository.deleteById(id);
	}
	
	@Test
	void test6()
	{
		//Delete all in batch(CRUD)
		professorRepository.deleteAllInBatch();
	}
	
}
