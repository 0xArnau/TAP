import users.*;
import messages.*;
import mailstore.*;


public class Main {
	public static void main(String [] args) throws Exception {
		System.out.println("\nHI\n");

		User user = new User("star", "arnau", 2000);
		System.out.println("\n\n" + user);
		User user1 = new User("star1", "arnau1", 2000);
		System.out.println("\n\n" + user);

		Message m1 = new Message(user.getUserName(), user1.getUserName(), "subject", "body");
		System.out.println("\n\n" + m1);
		Message m2 = new Message(user1.getUserName(), user.getUserName(), "subject", "body");
		System.out.println("\n\n" + m2);

		System.out.println(m2.compareTo(m1));
		System.out.println(m1.compareTo(m2));
		System.out.println(m2.compareTo(m2));

		MailStore ms1 = new MailStore();
		MailStore ms2 = new MailStoreOnFile();

		ms1.sendMail(user, m1);
		ms1.sendMail(user, m2);
		ms1.sendMail(user1, m2);
		//ms1.sendMail(user1, m2);
		System.out.println(ms1.getMail(user1));
		System.out.println(ms1.getMail(user));

		ms2.sendMail(user, m2);
		System.out.println(ms2.getMail(user1));
		System.out.println(ms2.getMail(user));

	}
}
