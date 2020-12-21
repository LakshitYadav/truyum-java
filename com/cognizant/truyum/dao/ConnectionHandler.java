package com.cognizant.truyum.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionHandler {
	
	public static Connection getConnection() {
		
		Connection cn=null;
		try {
			FileInputStream fileInputStream = new FileInputStream("src/connection.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			fileInputStream.close();
			
			String driver=properties.getProperty("driver");
			Class.forName(driver.trim());
			
			String url=properties.getProperty("connection-url");
			
			String username=properties.getProperty("user");
			String password=properties.getProperty("password");

			cn=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cn;
	}
}
