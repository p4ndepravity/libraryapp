package data;

import java.sql.SQLException;

import models.LogicalModel;

public class PatronsDB extends DBModel {
	public PatronsDB() throws Exception {
		super("patrons");
		singular = "patron";
		columnNames.add("patron_id");
		columnNames.add("last_name");
		columnNames.add("first_name");
		columnNames.add("street_address");
		columnNames.add("city");
		columnNames.add("state");
		columnNames.add("zip");
	}

	@Override
	public void add(LogicalModel obj) throws SQLException {
		columnNames.remove(0);
		super.add(obj);
		columnNames.add(0, "patron_id");
	}
}
