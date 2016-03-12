package menus;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.*;

public class SearchMenu extends Menu {
	public SearchMenu(BooksMenu bm) {
		this.options.add("Choose a search method: ");
		this.options.add("ID");
		this.options.add("Title");
		this.options.add("Author last name");
		this.options.add("Author first name");
		this.options.add("Rating");
		this.options.add("Return to previous menu");
	}

	public SearchMenu(PatronsMenu pm) {
		this.options.add("Choose a search method: ");
		this.options.add("ID");
		this.options.add("Last name");
		this.options.add("First name");
		this.options.add("Street Address");
		this.options.add("City");
		this.options.add("State (XX)");
		this.options.add("Zipcode");
		this.options.add("Return to previous menu");
	}

	public SearchMenu(TransactionsMenu tm) {
		this.options.add("Choose a search method: ");
		this.options.add("ID");
		this.options.add("Patron ID");
		this.options.add("Book ID");
		this.options.add("Date (mm-dd-yyyy)");
		this.options.add("Type");
		this.options.add("Return to previous menu");
	}

	@Override
	public void showOptions() {
		System.out.format("%s\n", this.options.get(0));
		for (int i = 1; i < this.options.size(); i++) {
			System.out.format("%d. %s\n", i, this.options.get(i));
		}
	}

	public void printResults(int choice, String response, BooksDB books) throws SQLException {
		books.display(choice, response);
		/*
		switch (choice) {
		case 1:
			books.displaySelected(response);
			return;
		case 2:
			books.returnBooksByTitle(response);
			return;
		case 3:
			books.returnBooksByAuthLastName(response);
			return;
		case 4:
			books.returnBooksByAuthFirstName(response);
			return;
		case 5:
			int rating;
			try {
				rating = Integer.parseInt(response);
			} catch (NumberFormatException e) {
				System.out.println("Rating must be an integer.");
				e.printStackTrace();
				return;
			}
			books.returnBooksByRating(rating);
			return;
		default:
			System.out.println("Unrecognized input.");
			return;
		}
		*/
	}

	public void printResults(int choice, String response, PatronsDB patrons) throws SQLException {
		switch (choice) {
		case 1:
			patrons.displaySelected(response);
			return;
		case 2:
			patrons.returnPatronsByLastName(response);
			return;
		case 3:
			patrons.returnPatronsByFirstName(response);
			return;
		case 4:
			patrons.returnPatronsByStreetAddress(response);
			return;
		case 5:
			patrons.returnPatronsByCity(response);
			return;
		case 6:
			patrons.returnPatronsByState(response);
			return;
		case 7:
			patrons.returnPatronsByZip(response);
		default:
			System.out.println("Unrecognized input.");
			return;
		}
	}

	public void printResults(int choice, String response, TransactionsDB transactions) throws SQLException {
		switch (choice) {
		case 1:
			transactions.displaySelected(response);
			return;
		case 2:
			transactions.returnTransactionsByPatron(response);
			return;
		case 3:
			transactions.returnTransactionsByBook(response);
			return;
		case 4:
			final DateFormat df_user = new SimpleDateFormat("MM-dd-yyyy");
			final DateFormat df_out = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = df_user.parse(response);
				response = df_out.format(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			transactions.returnTransactionsByDate(response);
			return;
		case 5:
			transactions.returnTransactionsByType(response);
			return;
		default:
			System.out.println("Unrecognized input.");
			return;
		}
	}
}
