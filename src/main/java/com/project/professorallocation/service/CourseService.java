package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.entity.Course;
import com.project.professorallocation.exception.ExceptionMessage;
import com.project.professorallocation.repository.CourseRepository;

@Service
public class CourseService {
	
	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) 
	{
		super();
		this.courseRepository = courseRepository;
	}
	
	public List<Course> findAll()
	{
		return courseRepository.findAll();
	}
	
	public Course findByCourseId(Long id)
	{
		return courseRepository.findById(id).orElse(null);
	}
	
	public List<Course> findByName(String name)
	{
		return courseRepository.findByNameContainingIgnoreCase(name);
	}
	
	public Course save(Course course)
	{
		course.setId(null);
		return saveInternal(course);
	}
	
	public Course update(Course course) throws ExceptionMessage
	{
		Long id = course.getId();
		if(id == null || !courseRepository.existsById(id))
		{
			throw new ExceptionMessage("Erro!");
		}
		
		return saveInternal(course);
	}
	
	public void deleteById(Long id)
	{
		if(id != null && courseRepository.existsById(id))
		{
			courseRepository.deleteById(id);
		}
	}
	
	public void deleteAll()
	{
		courseRepository.deleteAllInBatch();
	}
	
	private Course saveInternal(Course course)
	{	
		Course newCourse = courseRepository.save(course);
		return newCourse;
	}

}
