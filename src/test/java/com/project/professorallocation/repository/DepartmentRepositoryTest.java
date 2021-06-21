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

import com.project.professorallocation.entity.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	void test1()
	{
		//Create (CRUD)
		//From crontroller (Arrange)
		Department d = new Department();
		d.setId(null);
		/*d.setId(null);
		d.setName("Departamento de Economia");
		d.setProfessorList(null);
		
		//Service (Act)
		d.setId(null);
		Department newDepartment = departmentRepository.save(d);
		//return newDepartment;*/
		
		System.out.println(saveInternal(d));
		
//		p.setId(null);
//		Professor newProfessor = professorRepository.save(p);
//		Long departmentId = newProfessor.getDepartment().getId();
//		Department newDepartment = departmentRepository.findById(departmentId).orElse(null);
//		newProfessor.setDepartment(newDepartment);

	}
	
	@Test
	void test2()
	{
		//Read by Id
		
		Long id = 2L; //selecione o id desejado
		Optional<Department> optional = departmentRepository.findById(id);
		Department d = optional.orElse(new Department());
		
		System.out.println(d);
	}
	
	@Test
	void testByName()
	{
		String name = "Física";
		List<Department> list = departmentRepository.findByNameContainingIgnoreCase(name);
		
		System.out.println(list);
	}
	
	@Test
	void test3()
	{
		//FindAll Read (CRUD)
		List<Department> departmentList = departmentRepository.findAll();
		
		System.out.println(departmentList);
	}
	
	@Test
	void test4()
	{
		//Update (CRUD)
		Long id = 3L;
		Department department = new Department();
		
		if(!departmentRepository.existsById(id))
		{
			//throw exception;
		}
		
		System.out.println(saveInternal(department));
	}
	
	@Test
	void test5()
	{
		//Delete (CRUD)
		Long id = 4L;
		
		departmentRepository.deleteById(id);
	}
	
	@Test
	void test6()
	{
		//Delete all in batch(CRUD)
		departmentRepository.deleteAllInBatch();
	}
	
	private Department saveInternal(Department d)
	{
		d.setId(null);
		d.setName("Departamento de Economia");
		d.setProfessorList(null);
		
		Department newDepartment = departmentRepository.save(d);
		return newDepartment;
	}

}
