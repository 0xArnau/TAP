import users.*;

public class Main {
	public static void main(String [] args) throws Exception {
		System.out.println("\nHI\n");

		User user = new User("star", "arnau", "2000");

		System.out.println(user.getUserName());
		System.out.println(user.getName());
		System.out.println(user.getYear());

	}
}
