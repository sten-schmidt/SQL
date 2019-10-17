package net.stenschmidt.sqlexamples.mssql.groovy;

import groovy.sql.Sql

class SqlServerWinAuth {

	static main(args) {
		
		//Win-Auth
		//Error: Failed to load the sqljdbc_auth.dll cause : no sqljdbc_auth in java.library.path
		//Solution: Add Path to sqljdbc_auth.dll at BuildPath >> mssql-jdbc-<Version>.<JRE-Version>.jar >> Native library location
		def url = 'jdbc:sqlserver://localhost:1433;DatabaseName=DevTest;integratedSecurity=true;'
		def sql = Sql.newInstance(url)
		
		sql.eachRow('SELECT FirstName, LastName FROM [dbo].[Person] ORDER BY LastName, FirstName') { row ->
			println "$row.FirstName, $row.LastName"
		}
		
		sql.close()
	}
}
