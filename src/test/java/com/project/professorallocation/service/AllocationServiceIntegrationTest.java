package com.project.professorallocation.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.entity.Allocation;
import com.project.professorallocation.entity.Course;
import com.project.professorallocation.entity.Professor;
import com.project.professorallocation.exception.ExceptionMessage;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceIntegrationTest {
	
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	@Autowired
	AllocationService allocationService;
	
	@Test
	public void findAll()
	{
		//Act
		List<Allocation> allocations = allocationService.findAll();
		
		//Print
		allocations.forEach(System.out::println);
	}
	
	@Test
	public void findById()
	{
		//Arrange
		Long id = 3L;
		
		//Act && Print
		System.out.println(allocationService.findById(id));
	}
	
	@Test
	public void findByProfessor()
	{
		//Arrange
		Professor professor = new Professor();
		professor.setId(3L);
		
		//Act && Print
		System.out.println(allocationService.findByProfessor(professor.getId()));
	}
	
	@Test
	public void findByCourse()
	{
		//Arrange
		Course course = new Course();
		course.setId(3L);
		
		//Act && Print
		System.out.println(allocationService.findByCourse(course.getId()));
	}
	
	@Test
	public void save() throws ParseException
	{
		//Arrange
		Course course = new Course();
		course.setId(1L);
		
		Professor professor = new Professor();
		professor.setId(1L);
		
		Allocation alloc = new Allocation();
		
		alloc.setId(null);
		alloc.setDayOfWeek(DayOfWeek.FRIDAY);
		alloc.setTimeBegin(sdf.parse("10:00"));
		alloc.setTimeEnd(sdf.parse("12:00"));
		
		//Act && Print
		try {
			System.out.println(allocationService.save(alloc));;
		} catch (ExceptionMessage e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() throws ParseException
	{
		//Arrange
		Course course = new Course();
		course.setId(3L);
		
		Professor professor = new Professor();
		professor.setId(3L);
		
		Allocation alloc = new Allocation();
		alloc.setId(5L);
		alloc.setDayOfWeek(DayOfWeek.FRIDAY);
		alloc.setTimeBegin(sdf.parse("13:00"));
		alloc.setTimeEnd(sdf.parse("15:00"));
		
		//Act && Print
		System.out.println(allocationService.update(alloc));	
	}
	
	@Test
	public void deleteById()
	{
		//Arrange
		Long id = 5L;
		
		//Act
		allocationService.deleteById(id);
	}
	
	@Test
	public void deleteAll()
	{
		allocationService.deleteAll();
	}

}
