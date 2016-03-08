package menus;

public class AddMenu extends Menu {
	public AddMenu() {
		this.options.add("Enter the ID: ");
		this.options.add("Enter the Title: ");
		this.options.add("Enter the Author's Last Name");
		this.options.add("Enter the Author's First Name");
		this.options.add("Enter the Librarians Rating (1=worst 10=best): ");
	}
}
