package models;

import java.util.List;

public class Patron {
	private String id;
	private String lastName;
	private String firstName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	
	public Patron() {
		this.id = null;
		this.lastName = null;
		this.firstName = null;
		this.streetAddress = null;
		this.city = null;
		this.state = null;
		this.zip = null;
	}
	
	public Patron(List<String> values) {
		if (values.isEmpty()) return;
		this.lastName = values.get(0);
		this.firstName = values.get(1);
		this.streetAddress = values.get(2);
		this.city = values.get(3);
		this.state = values.get(4);
		this.zip = values.get(5);
	}
	
	public String getId() {
		return id;
	}
	public int IdAsInt() {
		int i;
		try {
			i = Integer.parseInt(id);
			return i;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public String lastName25() {
		return lastName.substring(0, Math.min(lastName.length(), 25));
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String firstName25() {
		return firstName.substring(0, Math.min(firstName.length(), 25));
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public String streetAddress40() {
		return streetAddress.substring(0, Math.min(streetAddress.length(), 40));
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public String city25() {
		return city.substring(0, Math.min(city.length(), 25));
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public String zip10() {
		return zip.substring(0, Math.min(zip.length(), 10));
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

	public boolean equals(Patron other) {
		return this.id == other.id;
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}
}
