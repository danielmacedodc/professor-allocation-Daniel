package com.project.professorallocation.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "course")
//@NoArgsConstructor
//@Data
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name_course", unique = true, nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "professor")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Allocation> allocation;
	
	public Course() {
		super();
	}

	public Course(Long id, String name, List<Allocation> allocation) {
		super();
		this.id = id;
		this.name = name;
		this.allocation = allocation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Allocation> getAllocation() {
		return allocation;
	}

	public void setAllocation(List<Allocation> allocation) {
		this.allocation = allocation;
	}
	
	
	}
