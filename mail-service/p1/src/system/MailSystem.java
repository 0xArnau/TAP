package system;

import mailbox.*;
import mailstore.*;
import messages.*;
import users.*;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class MailSystem {
	private static Map<User, MailBox> administrative = new HashMap<User, MailBox>();
	private static Map<String, User> users = new HashMap<String, User>();

	public static MailBox newUser(User u, MailStore store) {
		if (getExist(u.getUserName())) {
			System.out.println("=> username: " + u.getUserName() + " already exists.");
			return null;
		} else {

			MailBox box = new MailBox(u.getUserName(), store);
			administrative.put(u, box);
			users.put(u.getUserName(), u);
			System.out.println("=> user: " + u.getUserName() + " created.");
			return box;
		}
	}

	/**
	 * The mailbox can be retrieved later by giving the username (log in)
	 * 
	 * @return
	 */
	public static boolean logIn(String username) {
		User user = getUser(username);
		if (user == null)
			return false;
		return true;
	}

	/**
	 * Get all messages in the system.
	 * 
	 * @return
	 */
	public static List<Message> getAllMessages(boolean s) throws Exception {
		Stream<Message> all = Stream.empty();
		List<List<Message>> list=	 administrative.values().stream().map(p -> p.listMail()).collect(Collectors.toList());
		for (List<Message> l : list) {
			all = Stream.concat(all, l.stream());
		}
		return all.collect(Collectors.toList());
	}

	/**
	 * Get all users in the system.
	 * 
	 * @return
	 */
	public static Set<User> getAllUsers() {
		return administrative.keySet();
	}

	/**
	 * Filter messages globally: Get all messages in the system that fulfill a
	 * condition.
	 * 
	 * @return
	 */
	public static Stream<Message> filter(String word) throws Exception {
		return getAllMessages().stream().filter(str -> str.toString().contains(word));
	}

	/**
	 * Count total number of messages.
	 * 
	 * @return
	 */
	public static int countMessages() throws Exception {
		return getAllMessages().size();
	}

	public static Map<String, List<Message>> groupBySubject(boolean s) throws Exception {
		Map<String, List<Message>> all = getAllMessages().stream()
			.collect(Collectors.groupingBy(Message::getSubject));
		return all;
	}
	// Average messages per user.
	// @betaSAV
	public static float averageMessagesPerUser() throws Exception {
		return getAllMessages().size() / (float) getAllUsers().size();
	}

	// Group messages per subject. Any user.
	public static Stream<Message> filterSubject(String word) throws Exception {
		return getAllMessages().stream().filter(str -> str.getSubject().contains(word));
	}

	public static Stream<Message> filterBySender(String sender, Stream<Message> m) {
		return m.filter(p -> p.getFrom().equals(sender));
	}

	public static Stream<Message> filterBySender(String sender) throws Exception {
		return getAllMessages().stream().filter(p -> p.getFrom().equals(sender));
	}

	// Count the words of all messages from users with a particular name.
	public static int countWordsOfMessagesFromUser(String name, boolean s) throws Exception {
		return 	getAllMessages().stream().filter(p -> getUser(p.getFrom()).getName().equals(name))
			.map(x -> x.getBody().length())
			.collect(Collectors.summingInt(Integer::intValue));
	}

	// Get messages to users born before a certain year.
	// Receiver
	public static List<Message> usersBornBeforeXYear(int year) throws Exception {
		List<Message> list = new LinkedList<Message>();
		for (Map.Entry<User, MailBox> m : administrative.entrySet()) {
			try {
				if (m.getKey().getYear() < year) {
					list = Stream.concat(list.stream(), m.getValue().listMail().stream()).collect(Collectors.toList());
				}
			} catch (Exception e) {
			}
		}
		return list;
	}

	// Sender
	public static List<Message> usersBornAfterXYear(int year) throws Exception {
		List<Message> list = new LinkedList<Message>();
		for (Map.Entry<User, MailBox> m : administrative.entrySet()) {
			try {
				for (Message mess : m.getValue()) {
					if (getUser(mess.getTo()).getYear() > year)
						list.add(mess);
				}
			} catch (Exception e) {
			}
		}
		return list;
	}

	public static Stream<Message> containsXWordAndLessthanNWords(List<Message> m, String word, int n) throws Exception {
		return m.stream().filter(p -> p.toString().contains(word) && p.getBody().split(" ").length < n);
	}

	public static Stream<Message> containsXWord(List<Message> m, String word) throws Exception {
		return m.stream().filter(p -> p.toString().contains(word));
	}

	public static Stream<Message> lessthanNWords(List<Message> m, int n) throws Exception {
		return m.stream().filter(p -> p.getBody().split(" ").length < n);
	}

	public static Stream<Message> subjectSingleWord(List<Message> m) {
		return m.stream().filter(p -> p.getSubject().split(" ").length == 1);
	}

	public static User getUser(String username) {
		return users.get(username);
	}

	public static MailBox getMailBoxOfUser(String username) {
		try {
			return administrative.get(getUser(username));
		} catch (Exception e) {
			return null;
		}
	}

	public static MailBox getMailBoxOfUser(User username) {
		return administrative.get(username);
	}

	public static void printUsers() {
		administrative.forEach((key, value) -> System.out.println("User: " + key));
	}

	public static boolean getExist(String username) {
		return users.get(username) != null ? true : false;
	}

	public static void reset() {
		administrative = new HashMap<User, MailBox>();
		users = new HashMap<String, User>();
	}

}
