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

import com.project.professorallocation.entity.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	void test1()
	{
		//Create (CRUD)
		Department dept = new Department();
		departmentRepository.save(dept);
	}
	
	@Test
	void test2()
	{
		//Read by Id
		
		Long id = 1L; //selecione o id desejado
		Optional<Department> optional = departmentRepository.findById(id);
		Department d = optional.orElse(new Department());
		
		System.out.println(d.toString());
	}
	
	@Test
	void testByName()
	{
		String name = "Física";
		List<Department> list = departmentRepository.findByNameContainingIgnoreCase(name);
		
		System.out.println(list.toString());
	}
	
	@Test
	void test3()
	{
		//FindAll Read (CRUD)
		List<Department> departmentList = departmentRepository.findAll();
		
		System.out.println(departmentList.toString());
	}
	
	@Test
	void test4(Long id)
	{
		//Update (CRUD)
		if(departmentRepository.existsById(id))
		{
			Optional<Department> optional = departmentRepository.findById(id);
			Department d = optional.get();
			//User updates whatever he wants -> .setName/.setCPF/.setDepto
			departmentRepository.save(d);
		}
	}
	
	@Test
	void test5(Long id)
	{
		//Delete (CRUD)
		departmentRepository.deleteById(id);
	}
	
	@Test
	void test6()
	{
		//Delete all in batch(CRUD)
		departmentRepository.deleteAllInBatch();
	}	

}
