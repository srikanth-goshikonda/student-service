package com.sdc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

	private Integer roolNumber;
	private String firstName;
	private String lastName;
	private Integer age;
	private String emailId;

}
