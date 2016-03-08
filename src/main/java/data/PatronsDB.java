package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatronsDB {
	private Connection dbConnection = null;
	private Statement dbStatement = null;
	private ResultSet rs = null;
	
	public PatronsDB() throws Exception {

		try {
			// Get a connection to database
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "library", "library");

			// Create the statement
			dbStatement = dbConnection.createStatement();

			// Execute the SQL query
			rs = dbStatement.executeQuery("select * from Patrons order by patron_id desc");

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public void display() throws SQLException {
		String fname;
		String lname;
		
		while (rs.next()) {
			fname = rs.getString("first_name");
			lname = rs.getString("last_name");
			System.out.println(fname + " " + lname);
		}
	}
	
	public void close() throws SQLException {
		if (rs != null) {
			rs.close();
		}

		if (dbStatement != null) {
			dbStatement.close();
		}

		if (dbConnection != null) {
			dbConnection.close();
		}
	}
}
