package com.luv2code.testdb;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDbServlet {

	public static void main(String[] args) 
	{
		String jdbcUrl="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String user ="hbstudent";
		String pass="hbstudent";
		try
		{
		System.out.println("connecting to databases "+ jdbcUrl);
		Connection myConn=DriverManager.getConnection(jdbcUrl,user,pass);
		System.out.println("connection successful");
		}
        catch(Exception exc)
		{
        	exc.printStackTrace();
		}
	}

}