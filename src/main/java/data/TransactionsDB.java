package data;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

import models.Transaction;

public class TransactionsDB {

	private Connection dbConnection = null;
	private Statement dbStatement = null;
	private ResultSet rs = null;
	private List<String> columnNames = new ArrayList<String>();
	public static int count = 0;

	public TransactionsDB() throws Exception {
		columnNames.add("patron_id");
		columnNames.add("book_id");
		columnNames.add("transaction_date");
		columnNames.add("transaction_type");

		try {
			// Load the properties file
			Properties props = new Properties();
			props.load(new FileInputStream("db.properties"));

			// Read the props
			String theUser = props.getProperty("user");
			String thePassword = props.getProperty("password");
			String theDburl = props.getProperty("dburl");

			dbConnection = DriverManager.getConnection(theDburl, theUser, thePassword);

			// Create the statement
			dbStatement = dbConnection.createStatement();

			rs = dbStatement.executeQuery("select * from transactions");

			while (rs.next()) {
				count++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void add(Transaction t) throws SQLException {
		String s = "insert into transactions "
				 + "(patron_id,book_id,transaction_date,transaction_type) "
				 + "values ('" + t.getPatronid() + "',"
				 		 + "'" + t.getBookid() + "',"
				 		 + "'" + t.getDate_str() + "',"
				 		 + "'" + t.getType() + "')";
		try {
			dbStatement.executeUpdate(s);
			count++;
			System.out.println("Added transaction: " + t.toString());
		} catch (Exception e) {
			System.out.println("Failed to add Book");
			e.printStackTrace();
		}
		
		rs = dbStatement.executeQuery("select * from transactions");
	}
}


















