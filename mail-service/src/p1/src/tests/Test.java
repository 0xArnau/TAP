package p1.src.tests;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import p1.src.mailbox.MailBox;
import p1.src.mailstore.InMemory;
import p1.src.mailstore.MailStore;
import p1.src.messages.Message;
import p1.src.system.MailSystem;
import p1.src.users.User;

public class Test {
	public static void main(String[] args) throws Exception {
		MailSystem system = new MailSystem();

		System.out.println("MailSystem");
		User star = new User("star", "arnau", 2001);
		User beta = new User("beta", "sergi", 1999);
		MailStore sb = new InMemory();
		MailStore bb = new InMemory();
		MailBox starBox = system.newUser(star, sb);
		MailBox betaBox = system.newUser(beta, bb);

		system.getAllUsers().forEach(System.out::println);
		starBox.sendMail("star", "klk", "wtfklk");
		starBox.sendMail("beta", "klk", "wtfklk");
		betaBox.sendMail("beta", "wtf", "klk");
		betaBox.sendMail("beta", "wtf", "klk");
		betaBox.sendMail("star", "word", "klkklk");
		System.out.println("star");
		starBox.updateMail().forEach(System.out::println);
		System.out.println("beta");
		betaBox.updateMail().forEach(System.out::println);
		System.out.println("all");
		system.getAllMessages().forEach(System.out::println);
		System.out.println("starBox sorte");
		starBox.sortMail().forEach(System.out::println);
		System.out.println("betaBox sorte");
		betaBox.sortMail().forEach(System.out::println);
		System.out.println("Filter");
		system.filter("star").forEach(System.out::println);
		System.out.println("countmessages");
		System.out.println(system.countMessages());
		System.out.println("average");
		system.averageMessagesPerUser();
		System.out.println("filter per subject");
		system.filterSubject("klk").forEach(System.out::println);
		System.out.println("CountWords");
		System.out.println(system.countWordsOfMessagesFromUser("sergi"));
		System.out.println("usersBornBeforeXYear");
		system.usersBornBeforeXYear(2000).forEach(System.out::println);

		System.out.println("\n");
		starBox.updateMail().forEach(System.out::println);
		System.out.println("by sender");
		starBox.sortMailBySender().forEach(System.out::println);
		System.out.println("[7]");
		system.filterBySender("beta", system.filterSubject("word")).forEach(System.out::println);
		;

		System.out.println("[9]");
		System.out.println("[9.2]");
		system.usersBornBeforeXYear(2000).forEach(System.out::println);
		System.out.println("[9.1 + 9.2]");
		system.subjectSingleWord(system.usersBornAfterXYear(1999)).forEach(System.out::println);

		System.out.println("[10]\n");
		system.averageMessagesPerUser();

		System.out.println("[12]");
		Map<String, List<Message>> s = system.groupBySubject();
		for (Entry<String, List<Message>> m : s.entrySet()) {
			m.getValue().forEach(System.out::println);
		}

		System.out.println("CLI filter");
		system.containsXWordAndLessthanNWords(system.getAllMessages(), "klk", 10).forEach(System.out::println);

		System.out.println("XN");
		system.containsXWordAndLessthanNWords(system.getAllMessages(), "word", 10).forEach(System.out::println);
		System.out.println("X");
		system.containsXWord(system.getAllMessages(), "word").forEach(System.out::println);
		System.out.println("N");
		system.lessthanNWords(system.getAllMessages(), 10).forEach(System.out::println);
		System.out.println("getallmessages");
		system.getAllMessages().forEach(System.out::println);
		System.out.println("group");
		Map<String, List<Message>> g = system.groupBySubject();
		for (Entry<String, List<Message>> m : g.entrySet()) {
			m.getValue().forEach(System.out::println);
		}
		System.out.println("sum");
		System.out.println(system.countWordsOfMessagesFromUser("arnau"));
		System.out.println("get before year");
		system.usersBornBeforeXYear(2000).forEach(System.out::println);
		System.out.println("get after year");
		system.usersBornAfterXYear(2000).forEach(System.out::println);
		System.out.println("average");
		system.averageMessagesPerUser();
	}
}
