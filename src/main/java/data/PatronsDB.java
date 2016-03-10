package data;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import models.Book;
import models.Patron;

public class PatronsDB {
	private Connection dbConnection = null;
	private Statement dbStatement = null;
	private ResultSet rs = null;
	public static int count = 0;
	
	public PatronsDB() throws Exception {

		try {
			// Load the properties file
			Properties props = new Properties();
			props.load(new FileInputStream("db.properties"));

			// Read the props
			String theUser = props.getProperty("user");
			String thePassword = props.getProperty("password");
			String theDburl = props.getProperty("dburl");
			
			dbConnection = DriverManager.getConnection(theDburl, 
													   theUser, 
													   thePassword);

			// Create the statement
			dbStatement = dbConnection.createStatement();

			// Execute the SQL query
			rs = dbStatement.executeQuery("select * from patrons");

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
	
	public void add(Patron p) throws SQLException {
		String s = "insert into library.patrons "
				 + "(last_name,first_name,street_address,city,state,zip) "
				 + "values ('" + p.lastName25() + "',"
				 		 + "'" + p.firstName25() + "',"
				 		 + "'" + p.streetAddress40() + "',"
				 		 + "'" + p.city25() + "',"
				 		 + "'" + p.getState() + "',"
				 		 + "'" + p.zip10() + "')";
		try {
			dbStatement.executeUpdate(s);
			count++;
			System.out.println("Successfully added " + p.toString());
		} catch (Exception e) {
			System.out.println("Failed to add patron");
			e.getMessage();
		}
		rs = dbStatement.executeQuery("select * from patrons");
	}
	
	public void delete(String s) throws SQLException {
		int rowsAffected;
		try {
			rowsAffected = dbStatement.executeUpdate("delete from patrons where patron_id='" + s + "'");
			count--;
			System.out.println(rowsAffected > 0 ? "Successfully deleted patron." 
							 : "patron with id " + s + " not found.");
		} catch (Exception e) {
			System.out.println("Failed to delete patron.");
			e.printStackTrace();
		}
		rs = dbStatement.executeQuery("select * from patrons");
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
