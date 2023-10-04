package com.example.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

//helper class

@Repository // indicate that the class provides the mechanism for CRUD operation on objects;
//part of component
public class StudentDAOimpl implements StudentDAO {

	// define field for entity manager
	private EntityManager entityManager;

	// inject entity manager using constructor injection
	@Autowired
	public StudentDAOimpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	// implement save method
	@Override
	@Transactional // performing an update
	public void save(Student theStudent) {
		entityManager.persist(theStudent); // send to database

	}

	@Override
	public Student findById(Integer id) {

		return entityManager.find(Student.class, id);
	}

	@Override
	public List<Student> findAll() {
		// create query
		// !!on JPA Entity properties, not DB!!!
		TypedQuery<Student> theQuery =entityManager.createQuery("FROM Student",Student.class);
		
		// return query results 
		return theQuery.getResultList();
	}

	@Override
	public List<Student> findByLastName(String theLastName) {
		// create query
		// JPQL are prefixed with a colon;
		TypedQuery<Student> theQuery = entityManager.createQuery(
				"FROM Student WHERE lastName=:theData",Student.class);
				
		
		// set query parameters
		theQuery.setParameter("theData", theLastName);
		
		//return query results
		return theQuery.getResultList();
	}

	@Override
	@Transactional
	public void updateStudent(Student student) {
		entityManager.merge(student);
		
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Student theStudent = entityManager.find(Student.class, id);
		entityManager.remove(theStudent);
		
	}

	@Override
	@Transactional
	public int deleteAll() {
		int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
		return numRowsDeleted;
	}

}
