package net.stenschmidt.sqlexamples.mssql.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;

/**
 * https://docs.microsoft.com/de-de/sql/connect/jdbc/using-table-valued-parameters?view=sql-server-2017
 */
public class SqlServerTableValuedParameter {

	public static void main(String[] args) {

		String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=DevTest;integratedSecurity=true;";

		try {
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {

				SQLServerDataTable table = new SQLServerDataTable();
				table.addColumnMetadata("ID", java.sql.Types.INTEGER);
				table.addColumnMetadata("DirPath", java.sql.Types.VARCHAR);
				table.addColumnMetadata("FileCount", java.sql.Types.INTEGER);
				table.addColumnMetadata("ChangeDate", java.sql.Types.TIMESTAMP); //works only with datetime2?

				Timestamp ts = new Timestamp(new Date().getTime());
								
				table.addRow(1, "C:\\foo\\bar", 2, ts);
				table.addRow(2, "D:\\Data", 2343, ts);
				table.addRow(3, "/var/log/messages", 4711, ts);

				String sql = "DECLARE @RC INT = NULL; EXECUTE @RC = [dbo].[spInsertToTableB] ?; SELECT @RC AS Result";

				try (SQLServerPreparedStatement statement = (SQLServerPreparedStatement) connection.prepareStatement(sql)) {
					statement.setStructured(1, "dbo.TabTypeB", table);
					statement.setQueryTimeout(5);
					ResultSet resultSet = statement.executeQuery();
					while (resultSet.next()) {
						System.out.println(resultSet.getInt("Result"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
