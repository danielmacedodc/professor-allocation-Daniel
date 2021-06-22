package com.project.professorallocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.entity.Course;
import com.project.professorallocation.exception.ExceptionMessage;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceIntegrationTest {
	
	@Autowired
	CourseService courseService;
	
	@Test
	public void findAll()
	{
		//Act
		List<Course> courses = courseService.findAll();
		
		//Print
		courses.forEach(System.out::println);
	}
	
	@Test
	public void findById()
	{
		//Arrange
		Long id = 3L;
		
		//Act
		Course course = courseService.findByCourseId(id);
		
		//Print
		System.out.println(course);
	}
	
	@Test
	public void save()
	{
		//Arrange
		Course course = new Course();
		course.setId(null);
		course.setName("Mecânica Orbital");
		
		//Act
		course = courseService.save(course);
		
		//Print
		System.out.println(course);
	}
	
	@Test
	public void update()
	{
		//Arrange
		Course course = new Course();
		course.setId(4L);
		course.setName("Cosmologia");
		
		//Act && Print
		try
		{
			System.out.println(courseService.update(course));
		}
		catch(ExceptionMessage e)
		{
			System.out.println(e);
		}
	}
	
	@Test
	public void deleteById()
	{
		//Arrange, Act && Print
		Long id = 5L;
		courseService.deleteById(id);
	}
	
	@Test
	public void deleteAll()
	{
		courseService.deleteAll();
	}

}
