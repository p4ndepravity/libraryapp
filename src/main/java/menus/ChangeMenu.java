package menus;

import java.util.ArrayList;
import java.util.List;

public class ChangeMenu extends Menu {
	public ChangeMenu(BooksMenu bm) {
		this.options.add("Enter the book's ID: ");
		this.options.add("Which column?");
		this.options.add("ID");
		this.options.add("Title");
		this.options.add("Author's first name");
		this.options.add("Author's last name");
		this.options.add("Rating");
		this.options.add("Main Menu");
	}
	
	public ChangeMenu(PatronsMenu pm) {
		/* TODO
		this.options.add("Enter the book's ID: ");
		this.options.add("Which column?");
		this.options.add("1. Title");
		this.options.add("2. Author's first name");
		this.options.add("3. Author's last name");
		this.options.add("4. Rating");
		this.options.add("5. Main Menu");
		*/
	}
	
	public ChangeMenu(TransactionsMenu tm) {
		/* TODO
		this.options.add("Enter the book's ID: ");
		this.options.add("Which column?");
		this.options.add("1. Title");
		this.options.add("2. Author's first name");
		this.options.add("3. Author's last name");
		this.options.add("4. Rating");
		this.options.add("5. Main Menu");
		*/
	}

	@Override
	public List<String> askOptions() {
		List<String> responses = new ArrayList<String>();
		System.out.println(this.options.get(0));
		responses.add(this.respond());
		return responses;
	}
	
	@Override
	public void showOptions() {
		System.out.format("%s\n", this.options.get(1));
		for (int i=2;i<options.size();i++)
			System.out.format("%d. %s\n", i-1, this.options.get(i));
	}
}





















