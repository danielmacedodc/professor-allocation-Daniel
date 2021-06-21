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
		
		//From controller (Arrange)
		Course course = new Course();
		course.setId(null);
		
		//saveInternal(course);
		
		/*course.setName("Economia");
		course.setAllocation(null);
		
		//Service (Act)
		course.setId(null);
		Course newCourse = courseRepository.save(course);
		//return newCourse;*/
		
		System.out.println(saveInternal(course));
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
		Course course = new Course();
		
		if(!courseRepository.existsById(id))
		{
			//throw exception;
		}
		
		System.out.println(saveInternal(course));
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
	
	private Course saveInternal(Course course)
	{
		course.setId(null);
		course.setName("Economia");
		course.setAllocation(null);
		
		Course newCourse = courseRepository.save(course);
		return newCourse;
	}
}
