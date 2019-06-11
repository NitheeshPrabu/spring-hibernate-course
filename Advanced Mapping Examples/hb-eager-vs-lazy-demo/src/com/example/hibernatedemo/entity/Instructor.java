package com.example.hibernatedemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// annotate the class as an Entity and map to DB table
@Entity
@Table(name="instructor")
public class Instructor {

	// define the fields and annotate with table column names
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="email")
	private String email;

	// add the One-to-One mapping (foreign key) to InstructorDetail
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail instructorDetail;

	// refers to instructor property in the Course class
	// adding fetch will indicate how to load the associated entities
	// EAGER - loads all associations when instructor is loaded
	// LAZY - loads associations on demand
	@OneToMany(fetch = FetchType.LAZY,
				mappedBy = "instructor", cascade = {CascadeType.DETACH, CascadeType.MERGE,
				CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Course> courses;

	// create constructors

	public Instructor() {}

	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	// create getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	// create toString() method

	@Override
	public String toString() {
		return "Instructor{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", instructorDetail=" + instructorDetail +
				'}';
	}

	// add helper methods for bidirectional relationship
	public void add(Course newCourse) {
		if (courses == null) {
			courses = new ArrayList<>();
		}

		courses.add(newCourse);
		newCourse.setInstructor(this);
	}
}
