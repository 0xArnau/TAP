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
		MailBox mbsp = new MailBox("spam", ms);
		Message mbs = new Message("spam", "star", "subject", "body");
		Message sspam = new Message("star", "spam", "subject", "body");;
		Message m1 = new Message("star", "star", "subject", "body");
		Message mspam = new Message("spam", "spam", "subject", "body");
		Message mlong = new Message("star", "star", "subject", "bodybodybopdy123412312hsghjdgas");

		mb.sendMail("star", m1);
		System.out.println("1");
		mb.sendMail("star", mlong);
		System.out.println("2");
		mb.sendMail("star", sspam);
		System.out.println("3");
		mbsp.sendMail("star", mbs);
		System.out.println("4");
		mbsp.sendMail("star", mspam);
		System.out.println("5");
		mb.sendMail("spam", "k", "w");

		try {
			mb.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("AMB");
		AutomaticMailBox ambstar = new AutomaticMailBox("star", ms);
		AutomaticMailBox ambspam = new AutomaticMailBox("spam", ms);
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
			ambspam.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
