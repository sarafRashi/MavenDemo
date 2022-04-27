package com.employee.maven_project_1.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil 
{
	static Connection con=null;

	public Connection getConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //load the driver
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Employeedb","root","abcde123456789");
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}