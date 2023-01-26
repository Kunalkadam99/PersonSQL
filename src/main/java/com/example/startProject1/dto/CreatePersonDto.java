package com.example.startProject1.dto;

import javax.validation.constraints.NotBlank;

import com.example.startProject1.model.Person;

import lombok.Data;

@Data
public class CreatePersonDto {
	
	@NotBlank(message = "Firstname should not be empty")
	private String firstName;
	private String lastName;
	
	@NotBlank(message = "date of birth should not be empty")
	private String dob;
	
	public Person to() {
		return Person.builder().firstName(firstName).lastName(lastName).dob(dob).build();
	}
	
}
