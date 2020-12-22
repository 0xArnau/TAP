import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import mailbox.MailBox;
import mailstore.InMemory;
import mailstore.MailStore;
import mailstore.OnFile;
import messages.Message;
import system.MailSystem;
import users.User;

public class Test {
	public static void main(String[] args) throws Exception {

		
		System.out.println("MailSystem");
		User star = new User("star", "arnau", 2000);
		User beta = new User("beta", "sergi", 1999);
		MailStore sb = new InMemory();
		MailStore bb = new InMemory();
		MailBox starBox = MailSystem.newUser(star, sb);
		MailBox betaBox = MailSystem.newUser(beta, bb);

		MailSystem.getAllUsers().forEach(System.out::println);
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
		MailSystem.getAllMessages().forEach(System.out::println);
		System.out.println("starBox sorte");
		starBox.sortMail().forEach(System.out::println);
		System.out.println("betaBox sorte");
		betaBox.sortMail().forEach(System.out::println);
		System.out.println("Filter");
		MailSystem.filter("star").forEach(System.out::println);
		System.out.println("countmessages");
		System.out.println(MailSystem.countMessages());
		System.out.println("average");
		MailSystem.averageMessagesPerUser();
		System.out.println("filter per subject");
		MailSystem.filterSubject("klk").forEach(System.out::println);
		System.out.println("CountWords");
		System.out.println(MailSystem.countWordsOfMessagesFromUser("sergi"));
		System.out.println("usersBornBeforeXYear");
		MailSystem.usersBornBeforeXYear(2000).forEach(System.out::println);

		System.out.println("\n");
		starBox.updateMail().forEach(System.out::println);
		System.out.println("by sender");
		starBox.sortMailBySender().forEach(System.out::println);
		System.out.println("[7]");
		MailSystem.filterBySender("beta", MailSystem.filterSubject("word")).forEach(System.out::println);
		;

		System.out.println("[9]");
		System.out.println("[9.2]");
		MailSystem.usersBornBeforeXYear(2000).forEach(System.out::println);
		System.out.println("[9.1 + 9.2]");
		MailSystem.subjectSingleWord(MailSystem.usersBornAfterXYear(1999)).forEach(System.out::println);

		System.out.println("[10]\n" + MailSystem.averageMessagesPerUser());

		System.out.println("[12]");
		Map<String, List<Message>> s = MailSystem.groupBySubject();
		for (Entry<String, List<Message>> m : s.entrySet()) {
			m.getValue().forEach(System.out::println);
		}

		System.out.println("CLI filter");
		MailSystem.containsXWordAndLessthanNWords(MailSystem.getAllMessages(), "klk", 10).forEach(System.out::println);

		System.out.println("XN");
		MailSystem.containsXWordAndLessthanNWords(MailSystem.getAllMessages(), "word", 10).forEach(System.out::println);
		System.out.println("X");
		MailSystem.containsXWord(MailSystem.getAllMessages(), "word").forEach(System.out::println);
		System.out.println("N");
		MailSystem.lessthanNWords(MailSystem.getAllMessages(), 10).forEach(System.out::println);
		System.out.println("getallmessages");
		MailSystem.getAllMessages().forEach(System.out::println);
		System.out.println("getallmessages");
		MailSystem.getAllMessages(true).forEach(System.out::println);
		System.out.println("group");
		Map<String, List<Message>> g = MailSystem.groupBySubject();
		for (Entry<String, List<Message>> m : g.entrySet()) {
			m.getValue().forEach(System.out::println);
		}
		System.out.println("group");
		g = MailSystem.groupBySubject(true);
    for (Entry<String, List<Message>> m : g.entrySet()) {
      m.getValue().forEach(System.out::println);
		}
		System.out.println("sum");
		//MailSystem.countWordsOfMessagesFromUser("arnau", true).forEach(System.out::println);
		System.out.println(MailSystem.countWordsOfMessagesFromUser("arnau", true));
		System.out.println("sum");
		System.out.println(MailSystem.countWordsOfMessagesFromUser("arnau"));
		
	}
}
