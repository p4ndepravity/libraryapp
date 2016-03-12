package menus;

import java.text.*;
import java.util.*;

public abstract class Menu implements MenuInterface {
	protected List<String> options = new ArrayList<String>();
	final private DateFormat df_user = new SimpleDateFormat("MM-dd-yyyy");
	final private DateFormat df_out = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
	
	public String fixDate(String input) {
		Calendar d = Calendar.getInstance();
		try {
			d.setTime(df_user.parse(input));
			d.add(Calendar.HOUR_OF_DAY, 1);
			return df_out.format(d.getTime());
		} catch (ParseException e) {
			System.out.println("Unable to read date. Ensure mm-dd-yyyy format.");
			e.printStackTrace();
			System.out.println("\n\n");
			return null;
		}
	}
}
