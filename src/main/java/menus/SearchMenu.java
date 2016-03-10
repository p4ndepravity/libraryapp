package menus;

import java.sql.SQLException;

import data.BooksDB;

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
		/* TODO
		this.options.add("Choose a search method: ");
		this.options.add("ID");
		this.options.add("Title");
		this.options.add("Author last name");
		this.options.add("Author first name");
		this.options.add("Rating");
		this.options.add("Return to previous menu");
		*/
	}
	
	public SearchMenu(TransactionsMenu tm) {
		/* TODO
		this.options.add("Choose a search method: ");
		this.options.add("ID");
		this.options.add("Title");
		this.options.add("Author last name");
		this.options.add("Author first name");
		this.options.add("Rating");
		this.options.add("Return to previous menu");
		*/
	}
	
	@Override
	public void showOptions() {
		System.out.format("%s\n", this.options.get(0));
		for(int i=1;i<this.options.size();i++) {
			System.out.format("%d. %s\n", i, this.options.get(i));
		}
	}
	
	public void printResults(int choice, 
							 String response, 
							 BooksDB books) throws SQLException {
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
	}
}


























