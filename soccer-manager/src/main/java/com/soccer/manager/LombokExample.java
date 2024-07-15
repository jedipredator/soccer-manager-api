package com.soccer.manager;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

public class LombokExample {

	public static void main(String[] args) {

	    //Person person = new Person("423489232842558", "Marko", "Antoneti", 34);
	    //System.out.println("Person uses lombok logging: " + person.goToCity());
		
	    Student student = Student.builder("Adam", "Smith")
                				.numberOfPassedExams(3)
                				.build();

	    System.out.println("Student initialized with Builder.Default: " + student);
	}
}

@Slf4j
@AllArgsConstructor
@ToString
class Person {

  @NonNull String citizenId;
  @NonNull String firstName;
  @NonNull String lastName;
  int age;

  public String goToCity() {
    log.info("{} goes to city", this);
    return this + " goes to city";
  }
}

@Builder
@ToString
class Course {

	private String id = random();
	private String name;
	private int numberOfStudents = 1;
	
	private static String random() {
		// imagine this generates some random identifier
		return "sfsdf3243545";
	}
}

@Builder
@ToString
class Student {
	
	@Builder.Default private String id = random();
	private String firstName;
	private String lastName;
	@Builder.Default private String faculty = "Mathematical faculty";
	private int numberOfPassedExams;
	
	private static String random() {
		// imagine this generates some random identifier
		return "sfsdf3243545";
	}
	
	private static StudentBuilder builder() {
        return new StudentBuilder();
    }
	
	public static StudentBuilder builder(String firstName, String lastName) {
        return builder()
        			.firstName(firstName)
        			.lastName(lastName);
    }
}

@Builder(toBuilder = true)
class Exam {
	
	@Builder.Default private String id = random();
	@Builder.Default private String faculty = "Mathematical faculty";
	private String name;
	private String teacher;
	private List<String> students;
	@Builder.ObtainVia(method = "makeInfo") private String info;
	
	private static String random() {
		// imagine this generates some random identifier
		return "sfsdf3243545";
	}
	
	private String makeInfo() {
		return faculty + "\n"
				+ "Exam: " + name
				+ "Teacher: " + teacher
				+ "Number of students: " + students.size();
	}
}