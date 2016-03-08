package menus;

public class BooksMenu extends Menu {
	public BooksMenu() {
		this.options.add("Choose an option: ");
		this.options.add("1. Add a book");
		this.options.add("2. Search for a book");
		this.options.add("3. Change a book's details");
		this.options.add("4. Delete a book");
		this.options.add("5. Main Menu");
	}
}
