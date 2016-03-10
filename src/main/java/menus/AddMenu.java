package menus;

public class AddMenu extends Menu {
	public AddMenu(BooksMenu bm) {
		this.options.add("Enter the ID: ");
		this.options.add("Enter the title: ");
		this.options.add("Enter the author's last name: ");
		this.options.add("Enter the author's first name: ");
		this.options.add("Enter the librarians rating (1=worst 10=best): ");
	}
	
	public AddMenu(PatronsMenu pm) {
		this.options.add("Enter the last name: ");
		this.options.add("Enter the first name: ");
		this.options.add("Enter the street address: ");
		this.options.add("Enter the city: ");
		this.options.add("Enter the state(XX): ");
		this.options.add("Enter the zipcode: ");
	}
	
	public AddMenu(TransactionsMenu tm) {
		this.options.add("Enter the patron's ID: ");
		this.options.add("Enter the book's ID: ");
		this.options.add("Enter the transaction date (mm-dd-yyyy): ");
		this.options.add("Enter the transaction type (1=in 2=out): ");
	}
}
