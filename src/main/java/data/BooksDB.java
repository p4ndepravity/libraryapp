package data;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import models.Book;

public class BooksDB {

	private Connection dbConnection = null;
	private Statement dbStatement = null;
	private ResultSet rs = null;
	private List<String> columnNames = new ArrayList<String>();
	public static int count = 0;

	public BooksDB() throws Exception {
		columnNames.add("book_id");
		columnNames.add("title");
		columnNames.add("author_last_name");
		columnNames.add("author_first_name");
		columnNames.add("rating");
		
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
	
			rs = dbStatement.executeQuery("select * from Books "
										+ "order by author_last_name desc");
			while (rs.next()) {
				count++;
			}
	
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	public void add(String id, 
					String title, 
					String authLName, 
					String authFName, 
					int rating) throws SQLException {
		String s = "insert into library.books "
				+ "(book_id,title,author_last_name,author_first_name,rating) "
				+ "values ('" + id + "',"
						+ "'" + title + "',"
						+ "'" + authLName + "',"
						+ "'" + authFName + "',"
							  + rating + ")";
		try {
			dbStatement.executeUpdate(s);
			count++;
		} catch (Exception e) {
			System.out.println("Failed to add book.");
			e.printStackTrace();
		}
		rs = dbStatement.executeQuery("select * from Books "
									+ "order by author_last_name desc");
	}
	
	public void add(Book b) throws SQLException {
		String s = "insert into library.books "
				 + "(book_id,title,author_last_name,author_first_name,rating) "
				 + "values ('" + b.getid() + "',"
				 		 + "'" + b.title50() + "',"
				 		 + "'" + b.authLN25() + "',"
				 		 + "'" + b.authFN25() + "',"
				 		 	   + b.getRating() + ")";
		try {
			dbStatement.executeUpdate(s);
			count++;
			System.out.println("Added book with ID  " + b.getid());
		} catch (Exception e) {
			System.out.println("Failed to add Book");
			e.getMessage();
		}
		rs = dbStatement.executeQuery("select * from Books "
									+ "order by author_last_name desc");
	}

	public void displayAll() throws SQLException {
		String title;
		String author;

		rs = dbStatement.executeQuery("select * from Books "
									+ "order by author_last_name desc");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			title = rs.getString("title");
			author = rs.getString("author_first_name") + " " 
				   + rs.getString("author_last_name");
			System.out.println(title + " by " + author);
			System.out.println("----------------------------");
		}
	}

	public void displaySelected(String id) throws SQLException {
		String query = "select * from Books where book_id like '" + id + "'";
		rs = dbStatement.executeQuery(query);
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			System.out.println("ID: " + rs.getString("book_id"));
			System.out.println("Title: " + rs.getString("title"));
			String author = rs.getString("author_first_name") + " " 
						  + rs.getString("author_last_name");
			System.out.println("Author: " + author);
			System.out.println("Rating: " + rs.getString("rating"));
		}

		rs = dbStatement.executeQuery("select * from Books "
									+ "order by author_last_name desc");
	}
	
	public List<String> getSelected(String id) throws SQLException {
		String query = "select * from Books where book_id like '" + id + "'";
		rs = dbStatement.executeQuery(query);
		List<String> values = new ArrayList<String>();
		if (rs.next()) {
			values.add(rs.getString("book_id"));        
			values.add(rs.getString("title"));          
			values.add(rs.getString("author_first_name"));
			values.add(rs.getString("author_last_name"));
			values.add(rs.getString("rating"));
		} else {
			System.out.println("Nothing matching query found in database.");
		}

		rs = dbStatement.executeQuery("select * from Books "
									+ "order by author_last_name desc");
		return values;
	}

	public void returnBooksByAuthLastName(String s) throws SQLException {
		rs = dbStatement.executeQuery("select * from Books "
									+ "where author_last_name like '%" + s + "%' "
									+ "order by author_last_name desc");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		String title;
		String author;
		String id;
		while (rs.next()) {
			id = rs.getString("book_id");
			title = rs.getString("title");
			author = rs.getString("author_first_name") + " " 
				   + rs.getString("author_last_name");
			System.out.println(id + " " + title + " by " + author);
			System.out.println("----------------------------");
		}
	}

	public void returnBooksByAuthFirstName(String s) throws SQLException {
		rs = dbStatement.executeQuery("select * from Books "
									+ "where author_first_name like '%" + s + "%' "
									+ "order by author_first_name desc");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		String title;
		String author;
		String id;
		while (rs.next()) {
			id = rs.getString("book_id");
			title = rs.getString("title");
			author = rs.getString("author_first_name") + " " 
				   + rs.getString("author_last_name");
			System.out.println(id + " " + title + " by " + author);
			System.out.println("----------------------------");
		}
	}

	public void returnBooksByTitle(String s) throws SQLException {
		rs = dbStatement.executeQuery("select * from Books "
									+ "where title like '%" + s + "%' "
									+ "order by title desc");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		String title;
		String author;
		String id;
		while (rs.next()) {
			id = rs.getString("book_id");
			title = rs.getString("title");
			author = rs.getString("author_first_name") + " " 
				   + rs.getString("author_last_name");
			System.out.println(id + " " + title + " by " + author);
			System.out.println("----------------------------");
		}
	}
	
	public void returnBooksByRating(int choice) throws SQLException {
		rs = dbStatement.executeQuery("select * from Books "
									+ "where rating like " + choice + " "
									+ "order by author_last_name desc");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		String title;
		String author;
		String id;
		while (rs.next()) {
			id = rs.getString("book_id");
			title = rs.getString("title");
			author = rs.getString("author_first_name") + " " 
				   + rs.getString("author_last_name");
			System.out.println(id + " " + title + " by " + author);
			System.out.println("----------------------------");
		}
	}
	
	public void change(String id, 
					   int colNum, 
					   String newValue) throws SQLException {
		String query = "update Books set " + columnName(colNum-1) + "='" + newValue + "' "
					 + "where book_id='" + id + "'";
		try {
			dbStatement.executeUpdate(query);
			System.out.println("Book successfully updated.");
		} catch (Exception e) {
			System.out.println("Failed to update book.");
			e.printStackTrace();
		}

		rs = dbStatement.executeQuery("select * from Books "
									+ "order by author_last_name desc");
	}
	
	public void delete(String s) throws SQLException {
		int rowsAffected;
		String query = "delete from Books where book_id='" + s + "'";
		try {
			rowsAffected = dbStatement.executeUpdate(query);
			count--;
			System.out.println(rowsAffected > 0 ? "Successfully deleted book." 
							 : "Book with id " + s + " not found.");
		} catch (Exception e) {
			System.out.println("Failed to delete book.");
			e.printStackTrace();
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
	
	private String columnName(int index) {
		return columnNames.get(index);
	}
}


















