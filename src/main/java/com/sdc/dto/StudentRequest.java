package com.sdc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.sdc.model.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

	@NotBlank(message = "First Name should not be blank")
	private String firstName;
	@NotBlank(message = "Last Name should not be blank")
	private String lastName;
	@NotBlank(message = "Age should not be blank")
	@Min(value = 15, message = "minimum age should be 15")
	private Integer age;
	@NotBlank(message = "Email should not be blank")
	@Email(message = "Please provide valid email address")
	private String emailId;

	public Student convertToStudentEntity() {

		Student entity = new Student();
		entity.setAge(this.age);
		entity.setEmailId(this.emailId);
		entity.setFirstName(this.firstName);
		entity.setLastName(this.lastName);
		return entity;

	}
}
