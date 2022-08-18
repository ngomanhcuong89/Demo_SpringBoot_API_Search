package com.laptrinhjavaweb.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.laptrinhjavaweb.repository.jdbc.DistrictRepositoryJDBC;

public class DistrictRepositoryJDBCImpl implements DistrictRepositoryJDBC
{

	@Override
	public String getNameById(Long id) {
		String districtName = "";
		try 
		{
			
			Connection connection = HelperRepositoryJDBC.getConnection();
			String query = "select * from district d where d.id = "+id;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) 
			{
				districtName = rs.getString("d.name");
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		
		return districtName;
	}


}
