package com.robsmovies.RobsMovies.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class NonIntegrationUtils {
	
	Statement statement;
	Connection con;
	
	int[] jsonLocations = {0, 1, 2};
	int[] ids = {1, 2, 3};
	String[] titles = {"Jaws", "Jaws 2", "Jaws 3"};
	String[] descriptions = {"A movie about a shark that goes to Hollywood to find himself", "The shark from Jaws develops a drug problem and other Hollywood stars help him to get back on his fins", "The shark from Jaws and Jaws 2 returns to fight the Nazis by way of a time travel device that only allows famous sharks past its temporal barrier"};
	String[] directors = {"Steven Spielberg", "Steven Spielberg", "Steven Spielberg"};
	String[] country = {"USA", "Panama", "Ireland"};
	int[] year = {2009, 2010, 1978};
	double[] budget = {120.0, 120000, 120000};
	double[] rentalPrice = {1.5, 2.5, 1.0};
	String[] onLoan = {"y", "n", "y"};
	String[] picture = {"C:/Users/A00226084/Pictures/disregard females.jpg", "C:/Users/A00226084/Pictures/disregard females.jpg", "C:/Users/A00226084/Pictures/disregard females.jpg"};
	
	@Parameters
	public Object[] filmParams(){
Object[] paramsForTesting = new Object[3]; 
		System.out.println("here");
		for(int i = 0; i < paramsForTesting.length; i ++){
			paramsForTesting[i] = new Object[]{jsonLocations[i], ids[i], titles[i], descriptions[i], directors[i], country[i], year[i], budget[i], rentalPrice[i], onLoan[i], picture[i]};
		}
		return paramsForTesting;	
	}
	
	@Parameters
	public Object[] singleFilm(){
		return new Object[]{"0","1", "Jaws", "A movie about a shark that goes to Hollywood to find himself", "Steven Spielberg", "USA", 2009, 120.0, 1.5, "y", "C:/Users/A00226084/Pictures/disregard females.jpg"};
	}
	public void initialiseDatabase()
	{
//		try
//		{
//			Class.forName("com.mysql.jdbc.Driver");
//			String url="jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8121451";
//			con = DriverManager.getConnection(url, "sql8121451", "uxAcyTD6yr");
//			statement = con.createStatement();
//		}
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3307/movies";
			con = DriverManager.getConnection(url, "root", "admin");
			statement = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Error: Failed to connect to database\n");
			e.printStackTrace();
		}
	}
	
	@Parameters
	public Object[] searchParametersThatReturnOneFilm(){
		return new Object[]{
			new Object[]{"title", "Jaws", "Jaws"},
			new Object[]{"description", "The shark from Jaws develops a drug problem and other Hollywood stars help him to get back on his fins", "Jaws 2"},
			new Object[]{"budget", "120.0", "Jaws"},
			new Object[]{"country", "Ireland", "Jaws 3"},
			new Object[]{"yearMade", 1978, "Jaws 3"},
			new Object[]{"rentalPrice", 2.5, "Jaws 2"}
		};
	}
	
	public void insertData() throws FileNotFoundException, SQLException{
		initialiseDatabase();
		
			File sqlFile = new File("E:\\MSc Applied Software Engineering\\Web Technologies\\Project\\SQL Table.sql");
			Scanner sqlScanner = new Scanner(sqlFile).useDelimiter(";");
			while(sqlScanner.hasNext()){
				statement.execute(sqlScanner.next());
			}
			statement.execute("INSERT INTO movies VALUES (1, 'Jaws', 'A movie about a shark that goes to Hollywood to find himself', 'Steven Spielberg', 'USA', 2009, 120, 1.5, 'y', 'C:/Users/A00226084/Pictures/disregard females.jpg')");
			statement.execute("INSERT INTO movies VALUES (2, 'Jaws 2', 'The shark from Jaws develops a drug problem and other Hollywood stars help him to get back on his fins', 'Steven Spielberg', 'Panama', 2010, 120000, 2.5, 'n', 'C:/Users/A00226084/Pictures/disregard females.jpg')");
			statement.execute("INSERT INTO movies VALUES (3, 'Jaws 3', 'The shark from Jaws and Jaws 2 returns to fight the Nazis by way of a time travel device that only allows famous sharks past its temporal barrier', 'Steven Spielberg', 'Ireland', 1978, 120000, 1.0, 'y', 'C:/Users/A00226084/Pictures/disregard females.jpg')");
	}
	
	@Parameters
	public Object[] moreThanOneFilmReturnedFromSearch(){
		return new Object[]{
				new Object[]{0, "Jaws"},
				new Object[]{1, "Jaws 2"},
				new Object[]{2, "Jaws 3"}
		};
	}
}
