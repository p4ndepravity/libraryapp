package menus;

import java.text.*;
import java.util.Date;

public class TransactionsMenu extends Menu {
	public TransactionsMenu() {
		this.options.add("Choose an option: ");
		this.options.add("1. Add a transaction");
		this.options.add("2. Search for a transaction");
		this.options.add("3. Change a transaction's details");
		this.options.add("4. Delete a transaction");
		this.options.add("5. Main Menu");
	}
	
	public String searchDay(String userDate) {
		final DateFormat df_user = new SimpleDateFormat("MM-dd-yyyy");
		final DateFormat df_out = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = df_user.parse(userDate);
			return df_out.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
