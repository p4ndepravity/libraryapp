package data;

public class PatronsDB extends DBModel {
	public PatronsDB() throws Exception {
		super("patrons");
		singular = "patron";
		columnNames.add("last_name");
		columnNames.add("first_name");
		columnNames.add("street_address");
		columnNames.add("city");
		columnNames.add("state");
		columnNames.add("zip");
	}
}
