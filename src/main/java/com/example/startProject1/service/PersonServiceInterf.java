package com.example.startProject1.service;

import java.sql.SQLException;
import java.util.List;

import com.example.startProject1.dto.CreatePersonDto;
import com.example.startProject1.model.Person;

public interface PersonServiceInterf {
	
	void createPersonStatic(CreatePersonDto createPersonDto);
	
	Person getPersonById(int id);
	
	Person deletePerson(int id);
	
	List<Person> getAllPersons() throws SQLException;
	
}
