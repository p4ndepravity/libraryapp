package data;

import java.sql.*;
import models.Patron;

public class PatronsDB extends DBModel {
	public static int count = 0;
	
	public PatronsDB() throws Exception {
		super("patrons");
		while (rs.next()) {
			count++;
		}
	}
	
	public static int getCount() {
		return count;
	}
	
	public void displayAll() throws SQLException {
		String fname;
		String lname;
		
		while (rs.next()) {
			fname = rs.getString("first_name");
			lname = rs.getString("last_name");
			System.out.println(fname + " " + lname);
		}
	}
	
	public void displaySelected(String id) throws SQLException {
		String query = "select * from patrons where patron_id like '" + id + "'";
		rs = dbStatement.executeQuery(query);
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			System.out.println("ID: " + rs.getString("patron_id"));
			System.out.format("%s %s\n", rs.getString("first_name"),
										 rs.getString("last_name"));
			System.out.format("%s\n%s, %s %s\n", rs.getString("street_address"),
											 	 rs.getString("city"),
											 	 rs.getString("state"),
											 	 rs.getString("zip"));
			System.out.println("----------------------------");
		}

		rs = dbStatement.executeQuery("select * from patrons");
	}
	
	public void returnPatronsByLastName(String s) throws SQLException {
		rs = dbStatement.executeQuery("select * from patrons "
									+ "where last_name like '%" + s + "%' "
									+ "order by last_name");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			System.out.println("ID: " + rs.getString("patron_id"));
			System.out.format("%s %s\n", rs.getString("first_name"),
										 rs.getString("last_name"));
			System.out.format("%s\n%s, %s %s\n", rs.getString("street_address"),
											 	 rs.getString("city"),
											 	 rs.getString("state"),
											 	 rs.getString("zip"));
			System.out.println("----------------------------");
		}
		
		rs = dbStatement.executeQuery("select * from patrons");
	}
	
	public void returnPatronsByFirstName(String s) throws SQLException {
		rs = dbStatement.executeQuery("select * from patrons "
									+ "where first_name like '%" + s + "%' "
									+ "order by last_name");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			System.out.println("ID: " + rs.getString("patron_id"));
			System.out.format("%s %s\n", rs.getString("first_name"),
										 rs.getString("last_name"));
			System.out.format("%s\n%s, %s %s\n", rs.getString("street_address"),
											 	 rs.getString("city"),
											 	 rs.getString("state"),
											 	 rs.getString("zip"));
			System.out.println("----------------------------");
		}
		
		rs = dbStatement.executeQuery("select * from patrons");
	}
	
	public void returnPatronsByStreetAddress(String s) throws SQLException {
		rs = dbStatement.executeQuery("select * from patrons "
									+ "where street_address like '%" + s + "%'");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			System.out.println("ID: " + rs.getString("patron_id"));
			System.out.format("%s %s\n", rs.getString("first_name"),
										 rs.getString("last_name"));
			System.out.format("%s\n%s, %s %s\n", rs.getString("street_address"),
											 	 rs.getString("city"),
											 	 rs.getString("state"),
											 	 rs.getString("zip"));
			System.out.println("----------------------------");
		}
		
		rs = dbStatement.executeQuery("select * from patrons");
	}
	
	public void returnPatronsByCity(String s) throws SQLException {
		rs = dbStatement.executeQuery("select * from patrons "
									+ "where city like '%" + s + "%'");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			System.out.println("ID: " + rs.getString("patron_id"));
			System.out.format("%s %s\n", rs.getString("first_name"),
										 rs.getString("last_name"));
			System.out.format("%s\n%s, %s %s\n", rs.getString("street_address"),
											 	 rs.getString("city"),
											 	 rs.getString("state"),
											 	 rs.getString("zip"));
			System.out.println("----------------------------");
		}
		
		rs = dbStatement.executeQuery("select * from patrons");
	}
	
	public void returnPatronsByState(String s) throws SQLException {
		rs = dbStatement.executeQuery("select * from patrons "
									+ "where state like '%" + s + "%'");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			System.out.println("ID: " + rs.getString("patron_id"));
			System.out.format("%s %s\n", rs.getString("first_name"),
										 rs.getString("last_name"));
			System.out.format("%s\n%s, %s %s\n", rs.getString("street_address"),
											 	 rs.getString("city"),
											 	 rs.getString("state"),
											 	 rs.getString("zip"));
			System.out.println("----------------------------");
		}
		
		rs = dbStatement.executeQuery("select * from patrons");
	}
	
	public void returnPatronsByZip(String s) throws SQLException {
		rs = dbStatement.executeQuery("select * from patrons "
									+ "where zip like '%" + s + "%'");
		if (!rs.next()) {
			System.out.println("Nothing matching query found in database.");
			return;
		}
		rs.previous();
		
		while (rs.next()) {
			System.out.println("ID: " + rs.getString("patron_id"));
			System.out.format("%s %s\n", rs.getString("first_name"),
										 rs.getString("last_name"));
			System.out.format("%s\n%s, %s %s\n", rs.getString("street_address"),
											 	 rs.getString("city"),
											 	 rs.getString("state"),
											 	 rs.getString("zip"));
			System.out.println("----------------------------");
		}
		
		rs = dbStatement.executeQuery("select * from patrons");
	}
	
	public void change(String patronid, 
					   int columnNum, 
					   String newValue) throws SQLException {
		String query = "update patrons set " + columnName(columnNum-1) + "='" + newValue + "' "
				 + "where patron_id='" + patronid + "'";
		try {
			dbStatement.executeUpdate(query);
			System.out.println("Patron successfully updated.");
		} catch (Exception e) {
			System.out.println("Failed to update patron.");
			e.printStackTrace();
			System.out.println("\n\n");
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
			System.out.println("\n\n");
		}
		rs = dbStatement.executeQuery("select * from patrons");
	}
}
