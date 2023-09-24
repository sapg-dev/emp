package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Database {

	private Connection theConnection;
	private final String link = "jdbc:mysql://localhost:3306/mydb";
	private final String userName = "root";
	private final String pass = "1234";

	private static Database empDB = new Database();

	private Database() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // class name for MySQL Driver
			this.theConnection = DriverManager.getConnection(link, userName, pass);// retrieve database connection

		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "Failed to connect to the database. Contact administrator");
		}
	}

	public static Database getDB() {
		return empDB;
	}

	public Connection getConnection() {
		return this.theConnection;
	}
}