package menus;

import java.util.*;

public abstract class Menu implements MenuInterface {
	protected List<String> options = new ArrayList<String>();
	
	public void showOptions() {
		ListIterator<String> it = options.listIterator();
		while(it.hasNext()){
			System.out.format("%s\n",it.next());
		}
	}
	
	public List<String> askOptions() {
		List<String> responses = new ArrayList<String>();
		ListIterator<String> it = options.listIterator();
		while(it.hasNext()){
			System.out.println(it.next());
			responses.add(this.respond());
		}
		return responses;
	}
	
	public int choose() {
		Scanner in = new Scanner(System.in);
		int choice;
		try {
			choice = in.hasNextInt() ? Integer.parseInt(in.nextLine()) : -1;
		} catch (NumberFormatException e) {
			choice = -1;
		}
		return choice;
	}
	
	public String respond() {
		Scanner in = new Scanner(System.in);
		String response = in.nextLine();
		in.close();
		return response;
	}
}