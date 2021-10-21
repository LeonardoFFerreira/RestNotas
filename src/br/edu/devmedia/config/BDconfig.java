package br.edu.devmedia.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDconfig {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/notes_db", "postgres", "admin");

	}

}
