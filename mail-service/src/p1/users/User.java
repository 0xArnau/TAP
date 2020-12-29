package p1.users;

public class User {
	private String username;
	private String name;
	private int yearOfBirth;
	// ?private String passwd;

	/**
	 * Constructor de User
	 * 
	 * @param username Nombre del usuario.
	 * @param name     Nombre real del usuario.
	 * @param year     Año de nacimiento del usuario.
	 */
	public User(String username, String name, int year) {
		this.username = username;
		this.name = name;
		this.yearOfBirth = year;
	}

	/**
	 * Getter del nombre del usuario.
	 * 
	 * @return Devuelve el nombre del usuario.
	 */
	public String getUserName() {
		return username;
	}

	/**
	 * Getter del nombre real del usuario.
	 * 
	 * @return Devuelve el nombre real del usuario.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter del año de naciemiento del usuario.
	 * 
	 * @return Devuelve el año de naciemiento del usuario.
	 */
	public int getYear() {
		return yearOfBirth;
	}

	/**
	 * toString del usuario.
	 * 
	 * @return Devuelve el string con la información del usuario.
	 */
	public String toString() {
		return "Username: " + username + ", Name: " + name + ", Born: " + yearOfBirth;
	}
}
