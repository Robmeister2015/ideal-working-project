package com.robsmovies.RobsMovies.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class MoviesMain {

	Statement statement;
	Connection con;
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException{
		MoviesMain u = new MoviesMain();
		u.setUpDatabase();
		
		}
	
	public void setUpDatabase() throws FileNotFoundException, SQLException, ClassNotFoundException{
	initialiseDatabaseConnection();
		File sqlFile = new File("G:\\MSc Applied Software Engineering\\Web Technologies\\Project\\Product Table.sql");
		Scanner sqlFileScanner = new Scanner(sqlFile).useDelimiter(";");
		String sqlReader = "";
		while(sqlFileScanner.hasNext()){
			sqlReader = sqlFileScanner.next() + ";";
			statement.execute(sqlReader);
		}
		System.out.println(sqlReader.toString());
		statement.execute(sqlReader.toString());
	}

	public void initialiseDatabaseConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8121451";
		con = DriverManager.getConnection(url, "sql8121451", "uxAcyTD6yr");
		statement = con.createStatement();
	}
}
