package com.example.startProject1.repositoryimpl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.startProject1.model.Person;
import com.example.startProject1.repository.PersonRepositoryInterf;


@Repository
public class PersonRepostioryImpl implements PersonRepositoryInterf {
	
	private Connection connection;
	
	private PreparedStatement preparedStatement;
	
	private static Logger logger = LoggerFactory.getLogger(PersonRepostioryImpl.class);
	
	@Autowired
	public PersonRepostioryImpl(Connection connection) {
		this.connection = connection;
		createTable();
	}
	
	private void createTable() {
		
		try {
			Statement statement = connection.createStatement();
			statement.execute("create table if not exists person(id int primary key auto_increment,first_name varchar(20), last_name varchar(20), age int, dob varchar(12))");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void createPersonStatic(Person person) {
		
		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into person( first_name, last_name, age, dob) values ('Peter','David', 25, '1996-02-04')");
			logger.info("Insert Statement Result - {}",result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void createPerson(Person person) throws SQLException {
		this.preparedStatement = connection.prepareStatement("insert into person (first_name, last_name, age, dob) values (?,?,?,?)");
		preparedStatement.setString(1, person.getFirstName());
		preparedStatement.setString(1, person.getLastName());
		preparedStatement.setInt(1, person.getAge());
		preparedStatement.setString(1, person.getDob());
		
		int result = preparedStatement.executeUpdate();
		logger.info("Insert Statement Result - {}",result);
	}

	@Override
	public Person getPersonById(int id) {
		
		try {
			this.preparedStatement = connection.prepareStatement("select * person where id = ?");
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				return getPersonFromResultset(resultSet);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private Person getPersonFromResultset(ResultSet resultSet) throws SQLException {
		
		
		return Person.builder()
				.firstName(resultSet.getString("first_name"))
				.lastName(resultSet.getString("last_name"))
				.dob(resultSet.getString(5))
				.age(resultSet.getInt(4))
				.id(resultSet.getInt("id")).build();
		
	}

	@Override
	public boolean deletePerson(int id) {
		
		return false;
	}

	@Override
	public List<Person> getAllPersons() throws SQLException {
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("select * from person");
		
		List<Person> personList = new ArrayList<Person>();
		while(resultSet.next()) {
			Person person = getPersonFromResultset(resultSet);
			personList.add(person);
		}
		
		return personList;
	}

}
