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

// Os comentários nesta classe se aplicam as outras classes

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {
	
	@Autowired
	private AllocationRepository allocationRepository;
	
	@Test
	void test1()//problema
	{
		//Create (CRUD)
		// Falta preencher os dados de alocação, deixa o ID com NULL
		// Este teste da um erro pois fere a restrição de não nulo para os atributos
		Allocation aloc = new Allocation();
		// SAVE retorna dados, o correto seria pegar este resultado e fazer um println
		allocationRepository.save(aloc);
	}
	
	@Test
	void test2()//ok
	{
		//Read by Id
		
		Long id = 3L; //selecione o id desejado
		Optional<Allocation> optional = allocationRepository.findById(id);
		Allocation a = optional.orElse(new Allocation());
		
		System.out.println(a.toString());
	}
	
	@Test
	void testByProfessor()//ok
	{
		Long id = 3L;
		List<Allocation> listProf = allocationRepository.findByProfessorId(id);
		
		System.out.println(listProf.toString());
	}
	
	@Test
	void testByCourse()//ok
	{
		Long id = 3L;
		List<Allocation> listCourse = allocationRepository.findByCourseId(id);
		
		System.out.println(listCourse.toString());
	}
	
	@Test
	void test3()//ok
	{
		//FindAll Read (CRUD)
		List<Allocation> allocationList = allocationRepository.findAll();
		
		System.out.println(allocationList.toString());
	}
	
	@Test
	void test4()//+ou- ok
	{
		//Update (CRUD)
		Long id = 3L;
		
		if(allocationRepository.existsById(id))
		{
			// O fluxo não seria este. O correto seria ter uma alocação com o ID já existente,
			// modificar algum dado, como você falou no comentário, e salvar essa alocação.
			// De qualquer forma, o método serviu para entender o funcionamento do save em UPDATE.
			Optional<Allocation> optional = allocationRepository.findById(id);
			Allocation a = optional.get();
			//User updates whatever he wants -> .setName/.setCPF/.setDepto
			allocationRepository.save(a);
		}
	}
	
	@Test
	void test5()//ok
	{
		//Delete (CRUD)
		Long id = 2L;
		
		allocationRepository.deleteById(id);
	}
	
	@Test
	void test6()//ok
	{
		//Delete all in batch(CRUD)
		allocationRepository.deleteAllInBatch();
	}	
}
