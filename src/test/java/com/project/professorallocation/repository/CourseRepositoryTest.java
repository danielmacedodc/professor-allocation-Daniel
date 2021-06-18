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

import com.project.professorallocation.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTest {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	void test1()
	{
		//Create (CRUD)
		Course course = new Course();
		courseRepository.save(course);
	}
	
	@Test
	void test2()
	{
		//Read by Id
		
		Long id = 1L; //selecione o id desejado
		Optional<Course> optional = courseRepository.findById(id);
		Course c = optional.orElse(new Course());
		
		System.out.println(c);
	}
	
	@Test
	void testByName()
	{
		String name = "Álgebra";
		List<Course> list = courseRepository.findByNameContainingIgnoreCase(name);
		
		System.out.println(list);
	}
	
	@Test
	void test3()
	{
		//FindAll Read (CRUD)
		List<Course> courseList = courseRepository.findAll();
		
		System.out.println(courseList);
	}
	
	@Test
	void test4()
	{
		//Update (CRUD)
		Long id = 3L;
		
		if(courseRepository.existsById(id))
		{
			Optional<Course> optional = courseRepository.findById(id);
			Course c = optional.get();
			//User updates whatever he wants -> .setName/.setCPF/.setDepto
			courseRepository.save(c);
		}
	}
	
	@Test
	void test5()
	{
		//Delete (CRUD)
		Long id = 2L;
		
		courseRepository.deleteById(id);
	}
	
	@Test
	void test6()
	{
		//Delete all in batch(CRUD)
		courseRepository.deleteAllInBatch();
	}
}
