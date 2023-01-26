 package com.example.startProject1.serviceimpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.startProject1.dto.CreatePersonDto;
import com.example.startProject1.model.Person;
import com.example.startProject1.repository.PersonRepositoryInterf;
import com.example.startProject1.service.PersonServiceInterf;

@Service
public class PersonServiceImpl implements PersonServiceInterf {

	
	@Autowired
	PersonRepositoryInterf personRepositoryInterf;
	
	@Override
	public void createPersonStatic(CreatePersonDto createPersonDto) {
		Person person = createPersonDto.to();
		
		if(person.getAge() == null)
			person.setAge(calculateAgeFromDOB(person.getDob()));
//		personRepositoryInterf.createPerson(person);
		personRepositoryInterf.createPersonStatic(person);
	}

	private Integer calculateAgeFromDOB(String dob) {
		if(dob == null) {
			return null;
		}
		LocalDate dobDate = LocalDate.parse(dob);
		LocalDate currentDate = LocalDate.now();
	
		return Period.between(dobDate, currentDate).getYears();
	}
	
	@Override
	public Person getPersonById(int id) {
		personRepositoryInterf.getPersonById(id);
		return null;
	}

	@Override
	public Person deletePerson(int id) {
		personRepositoryInterf.deletePerson(id);
		return null;
	}

	@Override
	public List<Person> getAllPersons() throws SQLException {
		personRepositoryInterf.getAllPersons();
		return null;
	}

	
	
	
	
	
}
