package menus;

import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Menu implements MenuInterface {
	protected List<String> options = new ArrayList<String>();

	public void showOptions() {
		ListIterator<String> it = options.listIterator();
		while (it.hasNext()) {
			System.out.format("%s\n", it.next());
		}
	}

	public List<String> askOptions() {
		List<String> responses = new ArrayList<String>();
		ListIterator<String> it = options.listIterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			responses.add(this.respond());
		}
		return responses;
	}

	@SuppressWarnings("resource")
	public int choose() {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		line = line.equals("") ? Integer.toString(-1) : line;
		int choice;
		try {
			choice = Integer.parseInt(line);
		} catch (NumberFormatException e) {
			System.out.println("Unrecognized input.");
			choice = -1;
		}
		return choice;
	}

	@SuppressWarnings("resource")
	public String respond() {
		Scanner in = new Scanner(System.in);
		long millis = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date stamp = new Date(millis);
		String response = in.nextLine();
		response = response.equals("") ? "BLANK" + sdf.format(stamp) : response;
		return response;
	}
	
	public String respond(int index) {
		System.out.println("Enter the " + this.options.get(index));
		return this.respond();
	}
}
