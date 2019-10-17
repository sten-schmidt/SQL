package net.stenschmidt.sqlexamples.mssql.groovy;

import groovy.sql.Sql

class ForEachRow {

	static main(args) {
		
		def url = 'jdbc:sqlserver://localhost:1433;DatabaseName=DevTest;user=DbReader;password=***'
		def sql = Sql.newInstance(url)
		
		def rows = sql.rows('SELECT FirstName, LastName FROM [dbo].[Person] ORDER BY LastName, FirstName')
		println "Anzahl Datensätze: ${rows.size()}"
		
		rows.forEach({
			println "${it.LastName}, ${it.FirstName}"
		})
		
		sql.close()
	}
}
