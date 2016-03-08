package menus;

public class TransactionsMenu extends Menu {
	public TransactionsMenu() {
		this.options.add("Choose an option: ");
		this.options.add("1. Add a transaction");
		this.options.add("2. Search for a transaction");
		this.options.add("3. Change a transaction's details");
		this.options.add("4. Delete a transaction");
		this.options.add("5. Main Menu");
	}
}
