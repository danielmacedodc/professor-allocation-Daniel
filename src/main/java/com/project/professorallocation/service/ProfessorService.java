package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.repository.ProfessorRepository;
import com.project.professorallocation.entity.Department;
import com.project.professorallocation.entity.Professor;

@Service
public class ProfessorService {
	
	private final ProfessorRepository professorRepository;
	
	private final DepartmentService departmentService;

	public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService) 
	{
		super();
		this.professorRepository = professorRepository;
		this.departmentService = departmentService;
	}
	
	public List<Professor> findAll(String name)
	{
		if(name == null)
		{
			return professorRepository.findAll();
		}
		
		else
		{
			return professorRepository.findByNameContainingIgnoreCase(name);
		}
	}
	
	public Professor findById(Long id)
	{
		return professorRepository.findById(id).orElse(null);
	}
	
	public List<Professor> findByDepartment(Long departmentId)
	{
		return professorRepository.findByDepartmentId(departmentId);
	}
	
	public Professor save(Professor professor)
	{
		professor.setId(null);
		return saveInternal(professor);	
	}
	
	public Professor update(Professor professor)
	{
		Long id = professor.getId();
		
		if(id == null || !professorRepository.existsById(id))
		{
			return null;
		}
		
		return saveInternal(professor);
	}
	
	public void deleteById(Long id)
	{
		if(id != null && professorRepository.existsById(id))
		{
			professorRepository.deleteById(id);
		}
	}
	
	public void deleteAll()
	{
		professorRepository.deleteAllInBatch();
	}
	
	private Professor saveInternal(Professor professor)
	{
		Professor newProfessor = professorRepository.save(professor);
		
		Department newDepartment = newProfessor.getDepartment();
		newDepartment = departmentService.findByDepartmentId(newDepartment.getId());
		newProfessor.setDepartment(newDepartment);
		
		return newProfessor;
		
	}
	
	
	
	
	
}
