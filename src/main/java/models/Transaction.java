package models;

import java.text.*;
import java.util.*;

public class Transaction {
	private String id;
	private String patronid;
	private String bookid;
	private Date date;
	private String date_str;
	private String type;
	final private DateFormat df_out = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	final private DateFormat df_in = new SimpleDateFormat("MM-dd-yyyy");
	
	public Transaction(List<String> values) {
		if (values.isEmpty()) return;
		patronid = values.get(0);
		bookid = values.get(1);
		date_str = values.get(2);
		this.fixDate();
		type = values.get(3);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPatronid() {
		return patronid;
	}
	public void setPatronid(String patronid) {
		this.patronid = patronid;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDate_str() {
		return date_str;
	}
	public void setDate_str(String date_str) {
		this.date_str = date_str;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean equals(Transaction other) {
		return this.id == other.id;
	}
	@Override
	public String toString() {
		return this.date + " " + this.patronid + " " + this.bookid + " " + this.type;
	}
	public void fixDate() {
		try {
			date = df_in.parse(date_str);
			date_str = df_out.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
