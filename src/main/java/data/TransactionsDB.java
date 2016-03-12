package data;

public class TransactionsDB extends DBModel {
	public TransactionsDB() throws Exception {
		super("transactions");
		singular = "transaction";
		columnNames.add("patron_id");
		columnNames.add("book_id");
		columnNames.add("transaction_date");
		columnNames.add("transaction_type");
	}
}


















