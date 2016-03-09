package menus;

public class SearchMenu extends Menu {
	public SearchMenu(BooksMenu bm) {
		this.options.add("Choose a search method: ");
		this.options.add("1. ID");
		this.options.add("2. Title");
		this.options.add("3. Author last name");
		this.options.add("4. Author first name");
		this.options.add("5. Rating");
		this.options.add("6. Return to previous menu");
	}
	
	public SearchMenu(PatronsMenu pm) {
		/* TODO
		this.options.add("Choose a search method: ");
		this.options.add("1. ID");
		this.options.add("2. Title");
		this.options.add("3. Author last name");
		this.options.add("4. Author first name");
		this.options.add("5. Rating");
		this.options.add("6. Return to previous menu");
		*/
	}
	
	public SearchMenu(TransactionsMenu tm) {
		/* TODO
		this.options.add("Choose a search method: ");
		this.options.add("1. ID");
		this.options.add("2. Title");
		this.options.add("3. Author last name");
		this.options.add("4. Author first name");
		this.options.add("5. Rating");
		this.options.add("6. Return to previous menu");
		*/
	}
	
	
}
