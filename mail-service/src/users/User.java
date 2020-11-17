package users;

public class User {
	private String username;
	private String name;
	private int yearOfBirth;

	public User(String username, String name, int year) throws Exception {
		this.username = username;
		this.name = name;
		this.yearOfBirth = year;
	}
	/***/
	public String getUserName() {return username;}
	/***/
	public String getName() {return name;}
	/***/
	public int getYear() {return yearOfBirth;}
	/***/
	public String toString() {
		return "Username: " + username + ", Name: " + name
		+ ", Born: " + yearOfBirth;
	}
}
