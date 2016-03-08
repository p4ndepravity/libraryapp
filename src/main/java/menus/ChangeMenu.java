package menus;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ChangeMenu extends Menu {
	public ChangeMenu() {
		this.options.add("Enter the book's ID: ");
		this.options.add("Which column?");
		this.options.add("1. Title");
		this.options.add("2. Author's first name");
		this.options.add("3. Author's last name");
		this.options.add("4. Rating");
		this.options.add("5. Main Menu");
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
		ListIterator<String> it = options.subList(1, options.size()).listIterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
