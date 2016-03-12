package data;

public class BooksDB extends DBModel {
	public BooksDB() throws Exception  {
		super("books");
		singular = "book";
		columnNames.add("book_id");
		columnNames.add("title");
		columnNames.add("author_last_name");
		columnNames.add("author_first_name");
		columnNames.add("rating");
	}
}


















