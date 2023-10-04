package com.example.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.cruddemo.dao.StudentDAO;

import com.example.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);

//			readStudent(studentDAO);

//			queryStudents(studentDAO);

//			queryStudentsByLastName(studentDAO);
			
//			updateStudent(studentDAO);
			
//			deleteStudent(studentDAO);
			
//			deleteAllStudents(studentDAO);
		};

	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		int num=studentDAO.deleteAll();
		System.out.println(num); //print number of rows deleted
		
	}

	private void deleteStudent(StudentDAO studentDAO) {
		studentDAO.delete(4);
		
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on id:primary key
		Student myStudent = studentDAO.findById(4);
		
		// change first name
		myStudent.setFirstName("Duffy");
		
		// update student
		studentDAO.updateStudent(myStudent);
		
		
		// display updated student
		System.out.println(myStudent);
		
	}

	private void queryStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Duck");

		// display list of students
		for (Student student : theStudents)
			System.out.println(student);

	}

	private void queryStudents(StudentDAO studentDAO) {

		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// display list of students
		for (Student student : theStudents)
			System.out.println(student);

	}

	private void readStudent(StudentDAO studentDAO) {
		// create a student object
		Student tempStudent = new Student("Donald", "Duck", "dd@abc.com");

		// save a student object
		studentDAO.save(tempStudent);

		// display id of the student
		System.out.println(tempStudent.getId());

		// retrieve student based on id: primary key
		int id = tempStudent.getId();
		Student foundStudent = studentDAO.findById(id);

		// display student
		System.out.println(foundStudent);

	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Create student object ... ");
		Student tempStudent = new Student("Mary", "Mnb", "MaryMnb@abc.com");
		Student tempStudent1 = new Student("John", "Mnb", "JohnMnb@abc.com");
		Student tempStudent2 = new Student("Vasile", "Mnb", "MaryMnb@abc.com");

		// save the student object
		System.out.println("Saving ... ");
		studentDAO.save(tempStudent);
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);

		// display id of the saved student
		System.out.println("Saved student. ID= " + tempStudent.getId());
	}

}
