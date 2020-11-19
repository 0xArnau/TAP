import users.*;
import messages.*;
import system.MailSystem;
import mailstore.*;


public class Test {
	public static void main(String [] args) throws Exception {
		System.out.println("\nHI\n");

		User user = new User("star", "arnau", 2000);
		System.out.println("\n\n" + user);
		User user1 = new User("star1", "arnau1", 2000);
		System.out.println("\n\n" + user);
		User user2 = new User("star101", "arnau101", 2000000);
		System.out.println("\n\n" + user);

		MailSystem.newUser(user);
		MailSystem.newUser(user1);
		MailSystem.newUser(user2);

		Message m1 = new Message(user, user1, "subject", "body");
		System.out.println("\n\n" + m1);
		Message m2 = new Message(user1, user, "subject", "body");
		System.out.println("\n\n" + m2);
		Message m3 = new Message(user1, user2, "101", "101");
		System.out.println("\n\n" + m2);

		System.out.println(m2.compareTo(m1));
		System.out.println(m1.compareTo(m2));
		System.out.println(m2.compareTo(m2));

		MailStore ms1 = new MailStore();
		MailStore ms2 = new MailStoreOnFile();

		ms1.sendMail(user, m1);
		ms1.sendMail(user2, m3);
		ms1.sendMail(user1, m2);
		ms1.sendMail(user, m3);
		System.out.println(ms1.getMail(user1));
		System.out.println(ms1.getMail(user));

		ms2.sendMail(user, m2);
		ms2.sendMail(user, m3);
		ms2.sendMail(user, m3);
		System.out.println(ms2.getMail(user1));
		System.out.println(ms2.getMail(user));

		////

	}
}
