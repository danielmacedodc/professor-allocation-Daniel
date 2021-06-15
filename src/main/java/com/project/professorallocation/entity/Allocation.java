package com.project.professorallocation.entity;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "allocation")
@NoArgsConstructor
@Data
public class Allocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false, unique = false)
	private DayOfWeek dayOfWeek;
	
	@Temporal(value = TemporalType.TIME) //-> seria pra date | n precisa pra time (private Time var)
	@Column(nullable = false, unique = false)
	private Date timeBegin;
	
	@Temporal(value = TemporalType.TIME)
	@Column(nullable = false, unique = false)
	private Date timeEnd;
	
	@Column(name = "professor", nullable = false, unique = false)
	@OnDelete(action = OnDeleteAction.CASCADE) // essa lista é criada no banco como coluna ou tabela? precisa de nome ou outra especificação?
	@ManyToOne(optional = false)
	private Professor professor;
	
	@Column(name = "curso", nullable = false, unique = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(optional = false)
	private Course course;
	
	public Allocation() {
		super();
	}

	public Allocation(Long id, DayOfWeek dayOfWeek, Time timeBegin, Time timeEnd, Professor professor, Course course) {
		super();
		this.id = id;
		this.dayOfWeek = dayOfWeek;
		this.timeBegin = timeBegin;
		this.timeEnd = timeEnd;
		this.professor = professor;
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Date getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(Date timeBegin) {
		this.timeBegin = timeBegin;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
		

}
