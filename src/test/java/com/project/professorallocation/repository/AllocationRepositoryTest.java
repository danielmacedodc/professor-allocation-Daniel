package com.project.professorallocation.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.entity.Allocation;
import com.project.professorallocation.entity.Course;
import com.project.professorallocation.entity.Professor;
import com.project.professorallocation.exception.ExceptionMessage;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {
	
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	@Autowired
	private AllocationRepository allocationRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	void test1() throws ParseException
	{
		//Create (CRUD)
		Course course = new Course();
		course.setId(1L);
		
		Professor professor = new Professor();
		professor.setId(1L);
		
		Allocation alloc = new Allocation();
		
		alloc.setId(null);
		alloc.setDayOfWeek(DayOfWeek.MONDAY);
		alloc.setTimeBegin(sdf.parse("10:00"));
		alloc.setTimeEnd(sdf.parse("12:00"));
		
		try
		{
			System.out.println(saveInternal(alloc));
		}
		catch(ExceptionMessage e)
		{
			System.out.println(e);
		}
	}
	
	@Test
	void test2()
	{
		//Read by Id
		
		Long id = 3L; //selecione o id desejado
		Optional<Allocation> optional = allocationRepository.findById(id);
		Allocation a = optional.orElse(new Allocation());
		
		System.out.println(a.toString());
	}
	
	@Test
	void testByProfessor()
	{
		Long id = 3L;
		List<Allocation> listProf = allocationRepository.findByProfessorId(id);
		
		System.out.println(listProf.toString());
	}
	
	@Test
	void testByCourse()
	{
		Long id = 3L;
		List<Allocation> listCourse = allocationRepository.findByCourseId(id);
		
		System.out.println(listCourse.toString());
	}
	
	@Test
	void test3()
	{
		//FindAll Read (CRUD)
		List<Allocation> allocationList = allocationRepository.findAll();
		
		System.out.println(allocationList.toString());
	}
	
	@Test
	void test4() throws ExceptionMessage
	{
		//Update (CRUD)
		Long id = 3L;
		
		if(allocationRepository.existsById(id))
		{
			try
			{
				System.out.println(saveInternal(allocationRepository.getById(id)));
				
				//return saveInternal(allocationRepository.getById(id));
			}
			catch(ExceptionMessage e)
			{
				System.out.println(e);
			}

		}
	}
	
	@Test
	void test5()
	{
		//Delete (CRUD)
		Long id = 2L;
		
		allocationRepository.deleteById(id);
	}
	
	@Test
	void test6()
	{
		//Delete all in batch(CRUD)
		allocationRepository.deleteAllInBatch();
	}
	
	private Allocation saveInternal(Allocation allocation) throws ExceptionMessage
	{
		if(!hasAllocation(allocation))
		{
			Allocation newAllocation = allocationRepository.save(allocation);
			newAllocation.setId(null);
			
			Long courseId = newAllocation.getCourse().getId();
			Course newCourse = courseRepository.findById(courseId).orElse(null);
			newAllocation.setCourse(newCourse);
			
			Long professorId = newAllocation.getProfessor().getId();
			Professor newProfessor = professorRepository.findById(professorId).orElse(null);
			newAllocation.setProfessor(newProfessor);
			
			return newAllocation;
		}
		
		else
		{
			throw new ExceptionMessage("Erro na alocação");
			//throw new AllocationCollisionException(allocation);
		}
	
	}
	
	//avaliar se a alocação irá dar choque com outra
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
