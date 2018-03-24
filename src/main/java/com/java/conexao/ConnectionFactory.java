package com.java.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//return DriverManager.getConnection("jdbc:mysql://db-sitesindauma.mysql.uhserver.com/db_sitesindauma?autoreconect=true&useUnicode=yes&characterEncoding=Cp1252", "sitesindauma", "s1n2d3m4@");
			//return DriverManager.getConnection("jdbc:mysql://localhost/db_sitestefem?autoreconect=true&useUnicode=yes&characterEncoding=Cp1252", "root", "$3NH@");
			//return DriverManager.getConnection("jdbc:mysql://localhost/db_sitesindauma?autoreconect=true&useUnicode=yes&characterEncoding=Cp1252", "db_cadastroselect", "ti1234");
			return DriverManager.getConnection("jdbc:mysql://localhost/db_agendahora?autoReconnect=true", "root", "ti1234");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
        
    }
}