package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.entity.Department;
import com.project.professorallocation.exception.ExceptionMessage;
import com.project.professorallocation.repository.DepartmentRepository;


@Service
public class DepartmentService {
	
	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}
	
	public List<Department> findAll()
	{
		return departmentRepository.findAll();
	}
	
	public Department findByDepartmentId(Long id)
	{
		return departmentRepository.findById(id).orElse(null);
	}
	
	public List<Department> findByName(String name)
	{
		return departmentRepository.findByNameContainingIgnoreCase(name);
	}
	
	public Department save(Department department)
	{
		department.setId(null);
		return saveInternal(department);	
	}
	
	public Department update(Department department) throws ExceptionMessage
	{
		Long id = department.getId();
		if(id == null || !departmentRepository.existsById(id))
		{
			throw new ExceptionMessage("Erro!");
		}
		
		return saveInternal(department);
	}
	
	public void deleteById(Long id)
	{
		if(id != null && departmentRepository.existsById(id))
		{
			departmentRepository.deleteById(id);
		}
	}
	
	public void deleteAll()
	{
		departmentRepository.deleteAllInBatch();
	}
	
	private Department saveInternal(Department department)
	{	
		Department newDepartment = departmentRepository.save(department);
		return newDepartment;
	}	
	
}
