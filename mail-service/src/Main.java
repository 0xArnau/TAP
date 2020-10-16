import users.*;
import messages.*;
import mailstore.*;


public class Main {
	public static void main(String [] args) throws Exception {
		System.out.println("\nHI\n");

		User user = new User("star", "arnau", "2000");
		System.out.println("\n\n" + user);

		Message m1 = new Message("from","to", "subject", "body");
		System.out.println("\n\n" + m1);
		Message m2 = new Message("from","to", "subject", "body");
		System.out.println("\n\n" + m2);

		System.out.println(m2.compareTo(m1));
		System.out.println(m1.compareTo(m2));
		System.out.println(m2.compareTo(m2));
		

	}
}
