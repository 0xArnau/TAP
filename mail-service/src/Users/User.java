package users;

public class User {
	private String username;
	private String name;
	private String yearOfBirth;

	public User(String username, String name, String year) throws Exception {
		this.username = username;
		this.name = name;
		this.yearOfBirth = year;
	}

	public String getUserName() {return username;}
	public String getName() {return name;}
	public String getYear() {return yearOfBirth;}
}
