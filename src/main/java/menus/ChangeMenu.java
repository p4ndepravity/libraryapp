package menus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChangeMenu extends Menu {
	final private DateFormat df_user = new SimpleDateFormat("MM-dd-yyyy");
	final private DateFormat df_out = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
		this.options.add("Enter the patron's ID: ");
		this.options.add("Which column?");
		this.options.add("Last name");
		this.options.add("First name");
		this.options.add("Street address");
		this.options.add("City");
		this.options.add("State (XX)");
		this.options.add("Zipcode");
		this.options.add("Main Menu");
	}
	
	public ChangeMenu(TransactionsMenu tm) {
		this.options.add("Enter the transaction's ID: ");
		this.options.add("Which column?");
		this.options.add("Patron ID");
		this.options.add("Book ID");
		this.options.add("Transaction Date (mm-dd-yyyy)");
		this.options.add("Transaction Type");
		this.options.add("Main Menu");
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





















