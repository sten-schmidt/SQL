package net.stenschmidt.sqlexamples.mssql.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ForEachRow {

	public static void main(String[] args) {

		String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=DevTest;user=DbReader;password=***";

		try {
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {

				String sql = "SELECT FirstName, LastName FROM [dbo].[Person] ORDER BY LastName, FirstName";

				int timeout = 5;

				try (PreparedStatement statement = connection.prepareStatement(sql)) {
					statement.setQueryTimeout(timeout);
					ResultSet resultSet = statement.executeQuery();
					while (resultSet.next()) {
						System.out.println(resultSet.getString("LastName") + ", " + resultSet.getString("FirstName"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
