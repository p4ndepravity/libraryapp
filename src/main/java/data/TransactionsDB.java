package data;

import java.sql.*;
import models.Transaction;

public class TransactionsDB extends DBModel {
	public static int count = 0;

	public TransactionsDB() throws Exception {
		super("transactions");

	}
	
	public static int getCount() {
		return count;
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
			System.out.println("\n\n");
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
			System.out.println("\n\n");
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
	
	public void change(String id, 
			   		   int colNum, 
			   		   String newValue) throws SQLException {
		String query = "update transactions set " + columnName(colNum-1) + "='" + newValue + "' "
					 + "where transaction_id='" + id + "'";
		try {
			dbStatement.executeUpdate(query);
			System.out.println("Transaction successfully updated.");
		} catch (Exception e) {
			System.out.println("Failed to update transaction.");
			e.printStackTrace();
			System.out.println("\n\n");
		}

		rs = dbStatement.executeQuery("select * from transactions");
	}
	
	public void delete(String s) throws SQLException {
		int rowsAffected;
		String query = "delete from transactions where transaction_id='" + s + "'";
		try {
			rowsAffected = dbStatement.executeUpdate(query);
			count--;
			System.out.println(rowsAffected > 0 ? "Successfully deleted transaction." 
							 : "transaction with id " + s + " not found.");
		} catch (Exception e) {
			System.out.println("Failed to delete transaction.");
			e.printStackTrace();
			System.out.println("\n\n");
		}
	}
}


















