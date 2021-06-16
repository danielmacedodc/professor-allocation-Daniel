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
//@NoArgsConstructor Esta anotação serve justamente para que você não precise escrever o construtor vazio. Inclusive da erro.
@Data // Com esta anotação você não precisa definir escrever os getters e setters
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
	
	//@Column(name = "professor", nullable = false, unique = false)
	//^
	//|_ Essa linha não deve existir, o servidor nem chega a subir. Olha o erro abaixo. Você consegui fazer subir?
	/*
	 * 
	Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2021-06-15 21:34:14.107 ERROR 37004 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Invocation of init method failed; nested exception is org.hibernate.AnnotationException: @Column(s) not allowed on a @ManyToOne property: com.project.professorallocation.entity.Allocation.professor
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1786) ~[spring-beans-5.3.8.jar:5.3.8]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:602) ~[spring-beans-5.3.8.jar:5.3.8]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:524) ~[spring-beans-5.3.8.jar:5.3.8]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:335) ~[spring-beans-5.3.8.jar:5.3.8]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-5.3.8.jar:5.3.8]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:333) ~[spring-beans-5.3.8.jar:5.3.8]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208) ~[spring-beans-5.3.8.jar:5.3.8]
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154) ~[spring-context-5.3.8.jar:5.3.8]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:908) ~[spring-context-5.3.8.jar:5.3.8]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:583) ~[spring-context-5.3.8.jar:5.3.8]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:145) ~[spring-boot-2.5.1.jar:2.5.1]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:754) [spring-boot-2.5.1.jar:2.5.1]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:434) [spring-boot-2.5.1.jar:2.5.1]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:338) [spring-boot-2.5.1.jar:2.5.1]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1343) [spring-boot-2.5.1.jar:2.5.1]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1332) [spring-boot-2.5.1.jar:2.5.1]
	at com.project.professorallocation.ProfessorAllocationApplication.main(ProfessorAllocationApplication.java:10) [classes/:na]
Caused by: org.hibernate.AnnotationException: @Column(s) not allowed on a @ManyToOne property: com.project.professorallocation.entity.Allocation.professor
	at org.hibernate.cfg.AnnotationBinder.processElementAnnotations(AnnotationBinder.java:1778) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
	at org.hibernate.cfg.AnnotationBinder.processIdPropertiesIfNotAlready(AnnotationBinder.java:979) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
	at org.hibernate.cfg.AnnotationBinder.bindClass(AnnotationBinder.java:806) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
	at org.hibernate.boot.model.source.internal.annotations.AnnotationMetadataSourceProcessorImpl.processEntityHierarchies(AnnotationMetadataSourceProcessorImpl.java:248) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
	at org.hibernate.boot.model.process.spi.MetadataBuildingProcess$1.processEntityHierarchies(MetadataBuildingProcess.java:239) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
	at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:282) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:1224) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1255) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
	at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:58) ~[spring-orm-5.3.8.jar:5.3.8]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:365) ~[spring-orm-5.3.8.jar:5.3.8]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:409) ~[spring-orm-5.3.8.jar:5.3.8]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:396) ~[spring-orm-5.3.8.jar:5.3.8]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:341) ~[spring-orm-5.3.8.jar:5.3.8]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1845) ~[spring-beans-5.3.8.jar:5.3.8]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1782) ~[spring-beans-5.3.8.jar:5.3.8]
	... 16 common frames omitted
	
	 */
	@OnDelete(action = OnDeleteAction.CASCADE) // essa lista é criada no banco como coluna ou tabela? precisa de nome ou outra especificação?
	@ManyToOne(optional = false)
	private Professor professor;//
	
	//@Column(name = "curso", nullable = false, unique = false)
	// Mesma questão acima
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(optional = false)
	private Course course;
	
	public Allocation() {
		super();
	}

	// O construtor recebe Time, quando o mais indicado é receber Date
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
