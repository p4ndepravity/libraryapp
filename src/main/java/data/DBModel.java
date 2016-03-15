package data;

import java.io.*;
import java.nio.file.*;
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
	protected Properties props = new Properties();

	public DBModel(String type) {
		this.type = type;

		try {
			// Load the properties file
			createAndInitializePropertiesFile(props);

			// Read the properties
			String dbURL = props.getProperty("dburl");

			dbConnection = DriverManager.getConnection(dbURL);

			// Create the statement
			dbStatement = dbConnection.createStatement();
			
			// Add tables to database
			initializeDatabase();
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
		String query = String.format("insert into %s (%s) values (%s)", this.type, columns, values);

		try {
			dbStatement.executeUpdate(query);
			System.out.format("Successfully added to %s.\n", this.type);
		} catch (Exception e) {
			System.out.println("Failed to add Book");
			e.printStackTrace();
			System.out.println("\n\n");
		}
	}

	public void display(int choice, String response) throws SQLException {
		String query;
		query = String.format("select * from %s where %s %s order by %s desc", this.type, columnName(choice - 1),
				response, columnName(choice - 1));
		try {
			rs = dbStatement.executeQuery(query);
			boolean resultsReturned = false;
			while (rs.next()) {
				for (int i = 0; i < columnNames.size(); i++) {
					System.out.println(columnName(i) + ": " + rs.getString(i + 1));
				}
				System.out.println("----------------------------------");
				resultsReturned = true;
			}
			if (!resultsReturned) System.out.println("No matching records found in database.");
		} catch (Exception e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}

	public void change(String id, int colNum, String newValue) throws SQLException {
		String query;
		String idColumn = this.singular + "_id";
		query = String.format("update %s set %s='%s' where %s='%s'", this.type, columnName(colNum - 1), newValue,
				idColumn, id);
		try {
			int rowsAffected = dbStatement.executeUpdate(query);
			System.out.println(rowsAffected > 0 ? this.singular + " successfully updated."
					: String.format("Failed to update %s.", this.singular));
		} catch (Exception e) {
			System.out.println("Failed to update " + this.singular + ".");
			e.printStackTrace();
			System.out.println("\n\n");
		}
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
	
	private void createAndInitializePropertiesFile(Properties props) {
		Path dir = Paths.get(System.getProperty("user.home"), "LibraryApp");
		Path file = Paths.get(dir.toString(), "db.properties");
		String dbPath = dir.toString() + "/library.db";
		try {
			if (Files.notExists(file)) {
				if (Files.notExists(dir))
					Files.createDirectories(dir);
				Files.createFile(file);
				props.setProperty("dburl", "jdbc:sqlite:" + dbPath);
				props.store(new FileOutputStream(file.toString()), "");
				props.load(new FileInputStream(file.toString()));
			} else {
				props.load(new FileInputStream(file.toString()));
			}
		} catch (Exception e) {
			System.out.println("Unable to create properties file. " + "Check README for instructions.");
			e.printStackTrace();
		}
	}
	
	private void initializeDatabase() {
		String createBooks = "create table if not exists books "
						   + "(book_id text primary key, "
						    + "title text, "
						    + "author_last_name text, "
						    + "author_first_name text, "
						    + "rating integer)";
		String createPatrons = "create table if not exists patrons "
							 + "(patron_id integer primary key, "
							  + "last_name text, "
							  + "first_name text, "
							  + "street_address text, "
							  + "city text, "
							  + "state text, "
							  + "zip text)";
		String createTransactions = "create table if not exists transactions "
								  + "(transaction_id integer primary key, "
								   + "patron_id integer references patrons (patron_id) on delete cascade on update cascade, "
								   + "book_id text references books (book_id) on delete cascade on update cascade, "
								   + "transaction_date text, "
								   + "transaction_type integer check (transaction_type between 0 and 3))";
		try {
			dbStatement.execute(createBooks);
			dbStatement.execute(createPatrons);
			dbStatement.execute(createTransactions);
		} catch (SQLException e) {
			System.out.println("Failed to add tables to database.");
			e.printStackTrace();
		}
	}
}























