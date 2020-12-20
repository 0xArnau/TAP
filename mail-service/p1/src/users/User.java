package users;

public class User {
	private String username;
	private String name;
	private int yearOfBirth;
	//?private String passwd;

	public User(String username, String name, int year){
		this.username = username;
		this.name = name;
		this.yearOfBirth = year;
		if (year < 0)
			if (year == -2147483648)
				year += 1;
			yearOfBirth *= -1;

		
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
