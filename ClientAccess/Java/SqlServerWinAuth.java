package net.stenschmidt.sqlexamples.mssql.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlServerWinAuth {

	public static void main(String[] args) {

		// Win-Auth
		// Error: Failed to load the sqljdbc_auth.dll cause : no sqljdbc_auth in java.library.path
		// Solution: Add Path to sqljdbc_auth.dll at BuildPath >> mssql-jdbc-<Version>.<JRE-Version>.jar >> Native library location
		String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=DevTest;integratedSecurity=true;";

		try {
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {
				String sql = "SELECT FirstName, LastName FROM [dbo].[Person] ORDER BY LastName, FirstName";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						System.out.println(resultSet.getString("LastName") + ", " + resultSet.getString("FirstName"));
					}
				}
			}
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
}
