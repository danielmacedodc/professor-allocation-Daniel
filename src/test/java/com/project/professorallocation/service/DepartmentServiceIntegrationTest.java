package com.project.professorallocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.entity.Department;
import com.project.professorallocation.exception.ExceptionMessage;



@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceIntegrationTest {
	
	@Autowired
	DepartmentService departmentService;
	
	@Test
	public void findAll() 
	{
		//Arrange
		//String name = null;
		
		//Act
		List<Department> departments = departmentService.findAll();
		
		//Print
		departments.forEach(System.out::println);
	}
	
	@Test
	public void findById()
	{
		//Arrange
		Long id = 2L;
		
		//Act
		Department department = departmentService.findByDepartmentId(id);
		
		//Print
		System.out.println(department);
	}
	
	@Test
	public void save()
	{
		//Arrange
		Department department = new Department();
		department.setId(null);
		department.setName("Departamento de Ciências Espaciais");
		
		//Act
		department = departmentService.save(department);
		
		//Print
		System.out.println(department);
	}
	
	@Test
	public void update()
	{
		//Arrange
		Department department = new Department();
		department.setId(3L);
		department.setName("Departamento de Engenheria Aeroespacial");
		
		//Act
		try
		{
			department = departmentService.update(department);
		}
		catch(ExceptionMessage e)
		{
			System.out.println(e);
		}
		
		//Print
		System.out.println(department);
	}
	
	@Test
	public void deleteById()
	{
		//Arrange
		Long id = 3L;
		
		//Act
		departmentService.deleteById(id);
	}
	
	@Test
	public void deleteAll()
	{
		//Act
		departmentService.deleteAll();
	}
}
