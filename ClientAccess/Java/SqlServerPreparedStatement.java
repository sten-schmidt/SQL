package net.stenschmidt.sqlexamples.mssql.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

public class SqlServerPreparedStatement {

	public static void main(String[] args) {

		String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=DevTest;integratedSecurity=true;";

		try {
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {
				
				StringBuilder sql = new StringBuilder()
						.append("EXECUTE [dbo].[spInsertToTableC]")
						.append(" @DirPath=?")
						.append(",@FileCount=?")
						.append(",@ChangeDate=?");

				try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
					statement.setString(1, "/var/log/maillog");
					statement.setInt(2, 123);
					statement.setTimestamp(3, new Timestamp(new Date().getTime()));

					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						System.out.println(resultSet.getInt("Result"));
						System.out.println(resultSet.getString("DirPath"));
						System.out.println(resultSet.getInt("FileCount"));
						System.out.println(resultSet.getTimestamp("ChangeDate"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
