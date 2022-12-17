package com.sdc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sdc.dto.StudentResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer rollNumber;
	private String firstName;
	private String lastName;
	private Integer age;
	private String emailId;

	public StudentResponse convertToStudentResponse() {
		StudentResponse response = new StudentResponse();
		response.setRoolNumber(this.rollNumber);
		response.setAge(this.age);
		response.setEmailId(this.emailId);
		response.setFirstName(this.firstName);
		response.setLastName(this.lastName);
		return response;
	}
}
