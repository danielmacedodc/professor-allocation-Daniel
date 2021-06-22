package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.repository.AllocationRepository;
import com.project.professorallocation.entity.Allocation;
import com.project.professorallocation.entity.Course;
import com.project.professorallocation.entity.Professor;
import com.project.professorallocation.exception.ExceptionMessage;

@Service
public class AllocationService {
	
	private final AllocationRepository allocationRepository;
	private final CourseService courseService;
	private final ProfessorService professorService;
	
	public AllocationService(AllocationRepository allocationRepository, CourseService courseService,
			ProfessorService professorService) 
	{
		super();
		this.allocationRepository = allocationRepository;
		this.courseService = courseService;
		this.professorService = professorService;
	}
	
	public List<Allocation> findAll()
	{
		return allocationRepository.findAll();
	}
	
	public Allocation findById(Long id)
	{
		return allocationRepository.findById(id).orElse(null);
	}
	
	public List<Allocation> findByProfessor(Long professorId)
	{
		return allocationRepository.findByProfessorId(professorId);
	}
	
	public List<Allocation> findByCourse(Long courseId)
	{
		return allocationRepository.findByCourseId(courseId);
	}
	
	public Allocation save(Allocation allocation) throws ExceptionMessage
	{
		allocation.setId(null);
		return saveInternal(allocation);
	}
	
	public Allocation update(Allocation allocation)
	{
		Long id = allocation.getId();
		
		if(allocationRepository.existsById(id))
		{
			return saveInternal(allocation);
		}
		
		return null;
	}
	
	public void deleteById(Long id)
	{
		if(id != null && allocationRepository.existsById(id))
		{
			allocationRepository.deleteById(id);
		}
	}
	
	public void deleteAll()
	{
		allocationRepository.deleteAllInBatch();
	}
	
	private Allocation saveInternal(Allocation allocation)
	{
		if(!hasAllocation(allocation))
		{
			Allocation newAllocation = allocationRepository.save(allocation);
			
			Course newCourse = newAllocation.getCourse();
			newCourse = courseService.findByCourseId(newCourse.getId());
			newAllocation.setCourse(newCourse);
			
			Long professorId = newAllocation.getProfessor().getId();
			Professor newProfessor = professorService.findById(professorId);
			newAllocation.setProfessor(newProfessor);
			
			return newAllocation;
		}
		else
		{
			return null;
		}
	}
	
	boolean hasAllocation(Allocation newAllocation)
	{
		boolean hasCollision = false; //a princípio, não tem choque
		
		List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessor().getId()); //seleciona todas as alocações daquele professor (id) e salva numa lista
		
		for(Allocation currentAllocation : currentAllocations)
		{
			hasCollision = hasAllocation(currentAllocation, newAllocation); //avalia alguma alocação daquela lista choca com a alocação atual
			
			if(hasCollision)
			{
				break;
			}
		}
		
		return hasCollision;
	}
	
	//avaliar se ambas as alocações possuem mesmo horário, id e dia da semana
	private boolean hasAllocation(Allocation currentAllocation, Allocation newAllocation)
	{
		return !currentAllocation.getId().equals(newAllocation.getId()) // se for o mesmo id é update, logo é preciso retornar falso o equals para ser passado como true
				&& currentAllocation.getDayOfWeek() == newAllocation.getDayOfWeek()
				&& currentAllocation.getTimeBegin().compareTo(newAllocation.getTimeEnd()) < 0 //avalia se o término da aula de alocação é "antes" que o de outra 
				&& newAllocation.getTimeBegin().compareTo(currentAllocation.getTimeEnd()) < 0; //avalia se o começo da aula de alocação é "antes" de outra
	}
}
