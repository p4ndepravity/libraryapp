package menus;

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
}
