import mailbox.*;
import mailstore.*;
import users.*;
import messages.*;

public class TestP2 {
	public static void main(String[] args) {
		System.out.println("P2");

		User star = new User("star", "arnau", 2000);
		User spam = new User("spam", "spam", 2000);
		MailStore ms = new OnFile();
		MailBox mb = new MailBox("star", ms);
		MailBox mbspam = new MailBox("spam", ms);
		Message mbs = new Message("spam", "star", "subject", "body");
		Message m1 = new Message("star", "star", "subject", "body");
		Message mspam = new Message("spam", "spam", "subject", "body");
		Message mlong = new Message("star", "star", "subject", "bodybodybopdy123412312hsghjdgas");

		mb.sendMail("star", m1);
		System.out.println("1");
		mbspam.sendMail("spam", mspam);
		System.out.println("2");
		mb.sendMail("star", mlong);
		System.out.println("3");
		mbspam.sendMail("star", mbs);

		try {
			mb.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mbspam.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("AMB");
		AutomaticMailBox ambstar = new AutomaticMailBox("star", ms);
		AutomaticMailBox ambspam = new AutomaticMailBox("star", ms);
		System.out.println("TLF");
		TooLongFilter t = new TooLongFilter();
		System.out.println("SUF");
		SpamUserFilter s = new SpamUserFilter();
		ambstar.attach(t);
		ambstar.attach(s);
		ambspam.attach(t);
		ambspam.attach(s);

		System.out.println("STAR");
		try {
			ambstar.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SPAM");
		try {
			ambstar.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
