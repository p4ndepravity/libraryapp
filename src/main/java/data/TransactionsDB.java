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
	
	public void displaySelected(String id) throws SQLException {
		String query = "select * from transactions where transaction_id like '" + id + "'";
		rs = dbStatement.executeQuery(query);
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			System.out.println("ID: " + rs.getString("transaction_id"));
			System.out.println("Patron ID: " + rs.getString("patron_id"));
			System.out.println("Book ID: " + rs.getString("book_id"));
			System.out.println("Date: " + rs.getString("transaction_date"));
			System.out.println("Type (1=out 2=in): " + rs.getString("transaction_type"));
		}

		rs = dbStatement.executeQuery("select * from transactions");
	}
	
	public void returnTransactionsByPatron(String s) throws SQLException {
		int id = -1;
		try {
			id = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}
		rs = dbStatement.executeQuery("select * from transactions "
									+ "where patron_id like '" + id + "' "
									+ "order by transaction_date desc");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			System.out.println("ID: " + rs.getString("transaction_id"));
			System.out.println("Patron ID: " + rs.getString("patron_id"));
			System.out.println("Book ID: " + rs.getString("book_id"));
			System.out.println("Date: " + rs.getString("transaction_date"));
			System.out.println("Type (1=out 2=in): " + rs.getString("transaction_type"));
			System.out.println("----------------------------");
		}
		
		rs = dbStatement.executeQuery("select * from transactions");
	}
	
	public void returnTransactionsByBook(String s) throws SQLException {
		rs = dbStatement.executeQuery("select * from transactions "
									+ "where book_id like '" + s + "' "
									+ "order by transaction_date desc");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			System.out.println("ID: " + rs.getString("transaction_id"));
			System.out.println("Patron ID: " + rs.getString("patron_id"));
			System.out.println("Book ID: " + rs.getString("book_id"));
			System.out.println("Date: " + rs.getString("transaction_date"));
			System.out.println("Type (1=out 2=in): " + rs.getString("transaction_type"));
			System.out.println("----------------------------");
		}
		
		rs = dbStatement.executeQuery("select * from transactions");
	}
	
	public void returnTransactionsByDate(String s) throws SQLException {
		String beginning = s + " 00:00:00";
		String end = s + " 23:59:59";
		rs = dbStatement.executeQuery("select * from transactions "
									+ "where transaction_date between "
										+ "'" + beginning + "' and "
										+ "'" + end + "' "
									+ "order by transaction_date desc");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			System.out.println("ID: " + rs.getString("transaction_id"));
			System.out.println("Patron ID: " + rs.getString("patron_id"));
			System.out.println("Book ID: " + rs.getString("book_id"));
			System.out.println("Date: " + rs.getString("transaction_date"));
			System.out.println("Type (1=out 2=in): " + rs.getString("transaction_type"));
			System.out.println("----------------------------");
		}
		
		rs = dbStatement.executeQuery("select * from transactions");
	}
	
	public void returnTransactionsByType(String s) throws SQLException {
		int id = -1;
		try {
			id = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}
		rs = dbStatement.executeQuery("select * from transactions "
									+ "where transaction_type like '" + id + "' "
									+ "order by transaction_date desc");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			System.out.println("ID: " + rs.getString("transaction_id"));
			System.out.println("Patron ID: " + rs.getString("patron_id"));
			System.out.println("Book ID: " + rs.getString("book_id"));
			System.out.println("Date: " + rs.getString("transaction_date"));
			System.out.println("Type (1=out 2=in): " + rs.getString("transaction_type"));
			System.out.println("----------------------------");
		}
		
		rs = dbStatement.executeQuery("select * from transactions");
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


















