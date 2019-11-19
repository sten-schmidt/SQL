package net.stenschmidt.sqlexamples.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class OpenDatabaseConnectionExample {

	public static void main(String[] args) {

		String user = "scott";
		String passwd = "";
		
		System.out.printf("Please insert password for %s:", user);
		
		try (Scanner scanner = new Scanner(System.in)) {
			passwd = scanner.nextLine();	
		}
		
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", 
				user, passwd)) {

			if (connection != null) {
				System.out.println("Connected to the database!");
			} else {
				System.out.println("Failed to make connection!");
			}

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
