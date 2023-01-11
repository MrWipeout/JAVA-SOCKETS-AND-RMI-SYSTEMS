/*
 * Kargaki Maria 321/2015081
 * Exizoglou Athanasios 321/2015050
 * Project 1
 * 
 */


import java.io.Serializable;

public class User implements Serializable{ 
	private String username;
	private String password;
	private String name; 
	private String surname;
	private String number;
	private String email;
	private String rolos;
	

	public User() { // default constractor

	}

	public User(String username, String password) { 

		this.username = username;

		this.password = password;
	}
	
	public User(String name, String surname, String number, String email, String username, String password, String rolos) { //overload costructor
		this.name = name;
		this.surname = surname;
		this.number=number;
		this.email=email;
		this.username = username;
		this.password = password;
		this.rolos=rolos;
	}
	

	//setters kai getters
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getNumber() {
		return number;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getRolos() {
		return rolos;
	}

	public void setRolos(String rolos) {
		this.rolos = rolos;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", surname=" + surname
				+ ", number=" + number + ", email=" + email + ", rolos=" + rolos + "]";
	}

	
	

}
