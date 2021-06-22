package com.project.professorallocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.entity.Department;
import com.project.professorallocation.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceIntegrationTest {
	
	@Autowired
	ProfessorService professorService;
	
	@Test
	public void findAll()
	{
		//Arrange
		String name = "Daniel";
		
		//Act
		List<Professor> professors = professorService.findAll(name);
		
		//Print
		professors.forEach(System.out::println);
	}
	
	@Test
	public void findById()
	{
		//Arrange
		Long id = 3L;
		
		//Act
		Professor professor = professorService.findById(id);
		
		//Print
		System.out.println(professor);
	}
	
	@Test
	public void save()
	{
		//Arrange
		Department department = new Department();
		department.setId(3L);
		
		Professor professor = new Professor();
		professor.setId(null);
		professor.setName("Carl Sagan");
		professor.setCpf("99999999988");
		professor.setDepartment(department);
		
		//Act
		professor = professorService.save(professor);
		
		//Print
		System.out.println(professor);
	}
	
	@Test
	public void update()
	{
		//Arrange
		Department department = new Department();
		department.setId(1L);
		
		Professor professor = new Professor();
		professor.setId(5L);
		professor.setName("Wernher von Braun");
		professor.setCpf("98765432199");
		professor.setDepartment(department);
		
		//Act
		professor = professorService.update(professor);
		
		//Print
		System.out.println(professor);
	}
	
	@Test
	public void deleteById()
	{
		//Arrange
		Long id = 5L;
		
		//Act
		professorService.deleteById(id);
	}
	
	@Test
	public void deleteAll()
	{
		professorService.deleteAll();
	}
}
