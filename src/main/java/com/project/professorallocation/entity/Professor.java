package com.project.professorallocation.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "professor")
//@NoArgsConstructor Esta anotação serve justamente para que você não precise escrever o construtor vazio. Inclusive da erro.
@Data // Com esta anotação você não precisa definir escrever os getters e setters
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cpf", unique = true, nullable = false, length = 11)
	private String cpf;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(optional = false)
	private Department department;
	
	@OneToMany(mappedBy = "professor")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Allocation> allocation;
	
	
	public Professor() {
		super();
	}
	
	public Professor(Long id, String cpf, String name, Department department, List<Allocation> allocation) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.name = name;
		this.department = department;
		this.allocation = allocation;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Allocation> getAllocation() {
		return allocation;
	}

	public void setAllocation(List<Allocation> allocation) {
		this.allocation = allocation;
	}
	
	
}
