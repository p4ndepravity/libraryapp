package data;

import java.io.*;
import java.sql.*;
import java.util.*;

import models.LogicalModel;

public abstract class DBModel {
	protected Connection dbConnection = null;
	protected Statement dbStatement = null;
	protected ResultSet rs = null;
	protected List<String> columnNames = new ArrayList<String>();
	protected String type;
	protected String singular;

	public DBModel(String type) {
		this.type = type;
		switch (type) {
		case "books":
			singular = "book";
			columnNames.add("book_id");
			columnNames.add("title");
			columnNames.add("author_last_name");
			columnNames.add("author_first_name");
			columnNames.add("rating");
			break;
		case "patrons":
			singular = "patron";
			columnNames.add("last_name");
			columnNames.add("first_name");
			columnNames.add("street_address");
			columnNames.add("city");
			columnNames.add("state");
			columnNames.add("zip");
			break;
		case "transactions":
			singular = "transaction";
			columnNames.add("patron_id");
			columnNames.add("book_id");
			columnNames.add("transaction_date");
			columnNames.add("transaction_type");
			break;

		default:
			break;
		}
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

			rs = dbStatement.executeQuery("select * from " + type);

		} catch (FileNotFoundException e) {
			System.out.println("Properties file not found. " + "Check README for instructions.");
			e.printStackTrace();
			System.out.println("\n\n");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\n\n");
		} catch (SQLException e) {
			System.out.println("Unable to connect to database. " + "Check README for instructions.");
			e.printStackTrace();
			System.out.println("\n\n");
		}
	}

	public void add(LogicalModel obj) throws SQLException {
		String columns = "";
		ListIterator<String> it = columnNames.listIterator();
		while (it.hasNext()) {
			columns = columns + it.next();
			columns = columns + (it.hasNext() ? "," : "");
		}
		String values = "";
		ListIterator<String> it2 = obj.getValues().listIterator();
		while (it2.hasNext()) {
			values = values + "'" + it2.next() + "'";
			values = values + (it2.hasNext() ? "," : "");
		}
		String query = String.format("insert into %s (%s) values (%s)", 
									 this.type, columns, values);

		try {
			dbStatement.executeUpdate(query);
			System.out.format("Successfully added to %s.\n", this.type);
		} catch (Exception e) {
			System.out.println("Failed to add Book");
			e.printStackTrace();
			System.out.println("\n\n");
		}

		rs = dbStatement.executeQuery("select * from " + type);
	}
	
	public void display(int choice, String response) throws SQLException {
		String query;
		query = String.format("select * from %s where %s like '%%%s%%' order by %s desc", 
							  this.type, columnName(choice-1), response, columnName(choice-1));
		rs = dbStatement.executeQuery(query);
		if (!rs.next()) System.out.println("Nothing found matching those terms.");
		rs.previous();
		while(rs.next()) {
			for (int i=1;i<=columnNames.size();i++) {
				System.out.println(columnName(i-1) + ": " + rs.getString(i));
			}
			System.out.println("----------------------------------");
		}
	}
	
	public void change(String id, int colNum, String newValue) throws SQLException {
		String query;
		String idColumn = this.singular + "_id";
		query = String.format("update %s set %s='%s' where %s='%s'", 
							  this.type, columnName(colNum-1), newValue, idColumn, id);
		try {
			int rowsAffected = dbStatement.executeUpdate(query);
			System.out.println(rowsAffected > 0 ? this.singular + " successfully updated."
							 : String.format("Failed to update %s.", this.singular));
		} catch (Exception e) {
			System.out.println("Failed to update " + this.singular + ".");
			e.printStackTrace();
			System.out.println("\n\n");
		}

		rs = dbStatement.executeQuery("select * from Books " + "order by author_last_name desc");
	}
	
	public void delete(String s) throws SQLException {
		int rowsAffected;
		String idColumn = this.singular + "_id";
		String query = String.format("delete from %s where %s='%s'", this.type, idColumn, s);
		try {
			rowsAffected = dbStatement.executeUpdate(query);
			System.out.println(rowsAffected > 0 ? "Successfully deleted " + this.singular
							 : String.format("%s with id %s not found.", this.singular, s));
		} catch (Exception e) {
			System.out.println("Failed to delete " + this.singular);
			e.printStackTrace();
			System.out.println("\n\n");
		}
	}

	protected String columnName(int index) {
		return columnNames.get(index);
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
