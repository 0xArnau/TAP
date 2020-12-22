package system;

import mailbox.*;
import mailstore.*;
import messages.*;
import users.*;

import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class MailSystem {
	private Map<User, MailBox> administrative = new HashMap<User, MailBox>();
	private Map<String, User> users = new HashMap<String, User>();

	public MailBox newUser(User u, MailStore store) {
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
	public boolean logIn(String username) {
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
	public List<Message> getAllMessages() throws Exception {
		Stream<Message> all = Stream.empty();
		List<List<Message>> list = administrative.values().stream().map(p -> p.listMail()).collect(Collectors.toList());
		for (List<Message> l : list) {
			if (l == null)
				continue;
			all = Stream.concat(all, l.stream());
		}
		return all.collect(Collectors.toList());
	}

	/**
	 * Get all users in the system.
	 * 
	 * @return
	 */
	public Set<User> getAllUsers() {
		return administrative.keySet();
	}

	/**
	 * Filter messages globally: Get all messages in the system that fulfill a
	 * condition.
	 * 
	 * @return
	 */
	public Stream<Message> filter(String word) throws Exception {
		return getAllMessages().stream().filter(str -> str.toString().contains(word));
	}

	/**
	 * Count total number of messages.
	 * 
	 * @return
	 */
	public int countMessages() throws Exception {
		return getAllMessages().size();
	}

	public Map<String, List<Message>> groupBySubject() throws Exception {
		Map<String, List<Message>> all = getAllMessages().stream().collect(Collectors.groupingBy(Message::getSubject));
		return all;
	}

	// Average messages per user.
	// @betaSAV
	public void averageMessagesPerUser() throws Exception {
		float size = getAllMessages().size();
		for (Entry<User, MailBox> m : administrative.entrySet()) {
			if (m.getValue().listMail() == null)
				continue;
			System.out.print("Username: " + m.getKey().getUserName() + " average messages received: ");
			System.out.println(m.getValue().listMail().size() / size + " %");
		}
	}

	// Group messages per subject. Any user.
	public Stream<Message> filterSubject(String word) throws Exception {
		return getAllMessages().stream().filter(str -> str.getSubject().contains(word));
	}

	public Stream<Message> filterBySender(String sender, Stream<Message> m) {
		return m.filter(p -> p.getFrom().equals(sender));
	}

	public Stream<Message> filterBySender(String sender) throws Exception {
		return getAllMessages().stream().filter(p -> p.getFrom().equals(sender));
	}

	// Count the words of all messages from users with a particular name.
	public int countWordsOfMessagesFromUser(String name) throws Exception {
		return 	getAllMessages().stream().filter(p -> getUser(p.getFrom()).getName().equals(name))
			.map(x -> x.getBody().length())
			.collect(Collectors.summingInt(Integer::intValue));
	}

	// Get messages to users born before a certain year.
	// Receiver
	public List<Message> usersBornBeforeXYear(int year) throws Exception {
		return getAllMessages().stream()
			.filter(p -> getUser(p.getTo()).getYear() < year)
			.collect(Collectors.toList());
	}

	// Sender
	public List<Message> usersBornAfterXYear(int year) throws Exception {
		return getAllMessages().stream()
			.filter(p -> getUser(p.getFrom()).getYear() > year)
			.collect(Collectors.toList());
	}

	public Stream<Message> containsXWordAndLessthanNWords(List<Message> m, String word, int n) throws Exception {
		return m.stream().filter(p -> p.toString().contains(word) && p.getBody().split(" ").length < n);
	}

	public Stream<Message> containsXWord(List<Message> m, String word) throws Exception {
		return m.stream().filter(p -> p.toString().contains(word));
	}

	public Stream<Message> lessthanNWords(List<Message> m, int n) throws Exception {
		return m.stream().filter(p -> p.getBody().split(" ").length < n);
	}

	public Stream<Message> subjectSingleWord(List<Message> m) {
		return m.stream().filter(p -> p.getSubject().split(" ").length == 1);
	}

	public User getUser(String username) {
		return users.get(username);
	}

	public MailBox getMailBoxOfUser(String username) {
		try {
			return administrative.get(getUser(username));
		} catch (Exception e) {
			return null;
		}
	}

	public MailBox getMailBoxOfUser(User username) {
		return administrative.get(username);
	}

	public void printUsers() {
		administrative.forEach((key, value) -> System.out.println("User: " + key));
	}

	public boolean getExist(String username) {
		return users.get(username) != null ? true : false;
	}

	public void reset() {
		administrative = new HashMap<User, MailBox>();
		users = new HashMap<String, User>();
	}

}
