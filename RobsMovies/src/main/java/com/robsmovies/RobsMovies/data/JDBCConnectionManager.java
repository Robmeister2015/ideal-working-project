package com.robsmovies.RobsMovies.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCConnectionManager {

	public Connection con;
	public Statement statement;
	
	public void initiateDatabase()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8119595";
			con = DriverManager.getConnection(url, "sql8119595", "qNPE92heIb");
			statement = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Error: Failed to connect to database\n");
	//		e.printStackTrace();
		}
	}
	public Statement getStatement(){
		return statement;
	}
}
