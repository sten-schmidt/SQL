package net.stenschmidt.sqlexamples.mssql.groovy

import com.microsoft.sqlserver.jdbc.SQLServerException
import groovy.sql.Sql

class SqlServerQueryTimeout {
	
	static main(args) {

		def sql = null
		try {
			
			def url = 'jdbc:sqlserver://localhost:1433;DatabaseName=DevTest;user=DbReader;password=***'
			sql = Sql.newInstance(url)
			
			sql.withStatement {
				//Client will wait for xx seconds
				stmt -> stmt.queryTimeout = 6
			}
			
			//Call SP an set @DelaySeconds to xx seconds
			sql.eachRow('EXEC [dbo].[spGetPersons_SqlServerQueryTimeout] 5') { row ->
				println "$row.FirstName, $row.LastName"
			}
		} catch (SQLServerException e) {
			if (e.message.contains("Timeout")) {
				println "Timeout..."
			} else {
				throw e
			}
		} finally {
			sql?.close();
		}
	}
}
