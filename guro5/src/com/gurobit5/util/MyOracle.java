package com.gurobit5.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyOracle {

	private MyOracle(){}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
		return conn;
	}// getConnection()
	
}// MyOracle
	