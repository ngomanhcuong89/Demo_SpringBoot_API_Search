package com.laptrinhjavaweb.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelperRepositoryJDBC {
	public static Connection getConnection() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
    	String url       = "jdbc:mysql://localhost:3306/estatebasic";
    	String user      = "root";
    	String password  = "0123456789";
		Connection conn = DriverManager.getConnection(url,user,password);
		return conn;
	}
}
