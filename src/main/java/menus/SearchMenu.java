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

	public String respond(int choice) {
		System.out.println("Enter an " + this.options.get(choice));
		return super.respond();
	}
	
	public void printResults(int choice, 
							 String response, 
							 BooksDB books) throws SQLException {
		switch (choice) {
		case 1:
			books.displaySelected(response);
			break;
		case 2:
			books.returnBooksByTitle(response);
			break;
		case 3:
			books.returnBooksByAuthLastName(response);
			break;
		case 4:
			books.returnBooksByAuthFirstName(response);
			break;
		case 5:
			books.returnBooksByRating(Integer.parseInt(response));
			break;
		default:
			System.out.println("Unrecognized input.");
			break;
		}
	}
}


























