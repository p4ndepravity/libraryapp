package data;

import java.sql.*;
import models.Book;

public class BooksDB extends DBModel {
	private static int count = 0;

	public BooksDB() throws Exception  {
		super("books");
		while (rs.next()) {
			count++;
		}
	}

	public static int getCount() {
		return count;
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
			System.out.println("\n\n");
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
			System.out.println("\n\n");
		}
	}
}


















