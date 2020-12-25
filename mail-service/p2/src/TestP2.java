import java.util.HashSet;
import java.util.Set;

import mailbox.*;
import mailstore.*;
import users.*;
import messages.*;

public class TestP2 {
	public static void main(String[] args) {
		System.out.println("P2");

		User star = new User("star", "arnau", 2000);
		User spam = new User("spam", "spam", 2000);
		MailStore ms = new InMemory();
		MailBox mb = new MailBox("star", ms);
		MailBox mbsp = new MailBox("spam", ms);

		mb.sendMail("star", "subject", "body");
		System.out.println("1");
		mb.sendMail("star", "subject","123456789010111213141516171819202122232425262728290");
		System.out.println("2");
		mb.sendMail("star", "spam", "body");
		System.out.println("3");
		mbsp.sendMail("star", "spam", "spam");
		System.out.println("4");
		mbsp.sendMail("star", "spam", "wtf");
		System.out.println("5");
		mb.sendMail("spam", "k", "w");

		try {
			mb.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mbsp.updateMail().forEach(System.out::println);
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

		System.out.println("spammers");
		Set<String> st = ambstar.getSpammers();
		Set<String> sts =ambspam.getSpammers();
		Set<String> spammers = new HashSet<String>() {{
			addAll(st);
			addAll(sts);
		}};

		spammers.forEach(System.out::println);
	}
}
