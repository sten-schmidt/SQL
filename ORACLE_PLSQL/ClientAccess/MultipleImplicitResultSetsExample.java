package net.stenschmidt.sqlexamples.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

public class MultipleImplicitResultSetsExample {

	public static void main(String[] args) {

/*
	CREATE OR REPLACE PROCEDURE MultipleImplicitResultSetsExample
    (
        dirPath IN VARCHAR2,
        fileCount IN LONG,
        changeDate IN DATE
    )
    AS
        c1 SYS_REFCURSOR;
        c2 SYS_REFCURSOR;
	    
	BEGIN
	    
	    open c1 for
	    select 1 "Result", dirPath "DirPath", fileCount "FileCount", changeDate "ChangeDate" from dual;
	    DBMS_SQL.RETURN_RESULT(c1);
	
	    open c2 for
	    select 'Hello World from second ResultSet' "InfoMsg" from dual;
	    DBMS_SQL.RETURN_RESULT(c2);
	   
	END;
	/
	
	EXECUTE MultipleImplicitResultSetsExample('/var/log/maillog', 123, sysdate);
 */
		try {
			try (Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/orcl", "scott", "***")) {
				
				StringBuilder sql = new StringBuilder()
						.append("{ call MultipleImplicitResultSetsExample(?,?,?) }");

				try (CallableStatement statement = connection.prepareCall(sql.toString())) {

					statement.setString(1, "/var/log/maillog");
					statement.setInt(2, 123);
					statement.setTimestamp(3, new Timestamp(new Date().getTime()));					
					
					ResultSet resultSet;
					statement.executeQuery();

					int n = 0;
					while (statement.getMoreResults()) {
						resultSet = statement.getResultSet();
												
						while (resultSet.next()) {
							n++;
							System.out.printf("ResultSet #%d:\n", n);
							System.out.println("-------------");
							
							if (n == 1) {
								System.out.println(resultSet.getInt("Result"));
								System.out.println(resultSet.getString("DirPath"));
								System.out.println(resultSet.getInt("FileCount"));
								System.out.println(resultSet.getTimestamp("ChangeDate"));
							}
							
							if (n == 2) {
								System.out.println(resultSet.getString("InfoMsg"));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

