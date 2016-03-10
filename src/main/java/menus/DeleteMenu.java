package menus;

public class DeleteMenu extends Menu {
	public DeleteMenu(BooksMenu bm) {
		this.options.add("Enter the book ID: ");
	}
	
	public DeleteMenu(PatronsMenu pm) {
		this.options.add("Enter the patron ID: ");
	}
	
	public DeleteMenu(TransactionsMenu tm) {
		this.options.add("Enter the transaction ID: ");
	}
}
