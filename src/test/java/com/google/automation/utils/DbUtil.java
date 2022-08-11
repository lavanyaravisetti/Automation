package com.google.automation.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbUtil {
	Connection sqlConnection = null;
	ResultSet resultSet = null;
	Statement statement = null;
	PreparedStatement pst = null;

	/**
	 * Constructor
	 */
	public DbUtil() {

	}

	/**
	 * gets the connection object
	 * 
	 * @return
	 */
	private Connection getConncetion() {
		// write code to connect to db
		return sqlConnection;
	}

	/**
	 * executes the given querry
	 * 
	 * @param query
	 * @return
	 */
	public ResultSet runQuery(String query) {
		try {
			sqlConnection = getConncetion();
			System.out.println("Connected to the database!!! Getting table list...");
			statement = sqlConnection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * closes the all the objects
	 */
	public void close() {
		try {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (sqlConnection != null)
				sqlConnection.close();
		} catch (Exception ex) {
		}
	}

}
