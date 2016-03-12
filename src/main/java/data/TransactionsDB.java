package data;

import java.sql.SQLException;

import models.LogicalModel;

public class TransactionsDB extends DBModel {
	public TransactionsDB() throws Exception {
		super("transactions");
		singular = "transaction";
		columnNames.add("transaction_id");
		columnNames.add("patron_id");
		columnNames.add("book_id");
		columnNames.add("transaction_date");
		columnNames.add("transaction_type");
	}

	@Override
	public void add(LogicalModel obj) throws SQLException {
		columnNames.remove(0);
		super.add(obj);
		columnNames.add(0, "transaction_id");
	}
}


















