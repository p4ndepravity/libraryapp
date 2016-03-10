package models;

import java.util.List;

public class Book {
	private String id;
	private String title;
	private String authorLastName;
	private String authorFirstName;
	private int rating;
	
	public Book() {
		setid("none");
		setTitle("title");
		setAuthorLastName("lastname");
		setAuthorFirstName("firstname");
		setRating(5);
	}
	
	public Book(List<String> values) {
		if (values.isEmpty()) return;
		this.id = values.get(0);
		this.title = values.get(1);
		this.authorLastName = values.get(2);
		this.authorFirstName = values.get(3);
		try {
			this.rating = Integer.parseInt(values.get(4));
		} catch (NumberFormatException e) {
			this.rating = 5;
		}
	}

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public String title50() {
		return title.substring(0, Math.min(title.length(), 50));
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}
	
	public String authLN25() {
		return authorLastName.substring(0, Math.min(authorLastName.length(), 25));
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	public String getAuthorFirstName() {
		return authorFirstName;
	}
	
	public String authFN25() {
		return authorFirstName.substring(0, Math.min(authorFirstName.length(), 25));
	}

	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public boolean equals(Book other) {
		return this.id == other.id ? true : false;
	}

	@Override
	public String toString() {
		return this.title + " by " + this.authorFirstName + " " + this.authorLastName;
	}
}
