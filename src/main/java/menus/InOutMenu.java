package menus;

import java.text.*;
import java.util.*;

public class InOutMenu extends Menu {
	final private DateFormat df_in = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
	
	public InOutMenu(int type) {
		this.options.add("Enter the patron's ID: ");
		this.options.add("Enter the book's ID: ");
		Date now = new Date();
		this.options.add(df_in.format(now));
		// type: 1=out 2=in
		this.options.add(Integer.toString(type));
	}

	@Override
	public List<String> askOptions() {
		List<String> responses = new ArrayList<String>();
		ListIterator<String> it = this.options.subList(0, 2).listIterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			responses.add(this.respond());
		}
		it = this.options.subList(2, 4).listIterator();
		while (it.hasNext()) {
			responses.add(it.next());
		}
		return responses;
	}
}
