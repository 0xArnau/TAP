package system;

import mailbox.*;
import mailstore.*;
import messages.*;
import users.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class MailSystem {
	private static Map<User, MailBox> administrative = new HashMap<User, MailBox>();
	private static Map<String, User> users = new HashMap<String,User>();
	private static boolean memory = true;

	public static MailBox newUser(User u){
		if (getExist(u)) {
			System.out.println("=> username: " + u.getUserName() + " already exists.");
			u = null;
			return null;
		} else {
			MailStore store;
			if (memory)
				store = new InMemory();
			else
				store = new OnFile();
			MailBox box = new MailBox(u.getUserName(), store);
			administrative.put(u,box);
			users.put(u.getUserName(), u);
			System.out.println("=> user: " + u.getUserName() + " created.");
			return box;
		}
	}
	public static MailBox newUser(String username, String name, int  year){
		if (getExist(username)) {
			System.out.println("=> username: " + username + " already exists.");
			return null;
		} else {
			MailStore store;
			if (memory)
				store = new InMemory();
			else
				store = new OnFile();
			User u = new User(username, name, year);
			MailBox box = new MailBox(u.getUserName(), store);
			administrative.put(u,box);
			users.put(u.getUserName(), u);
			System.out.println("=> user: " + u.getUserName() + " created.");
			return box;
		}
	}

	public static void setMemory(boolean memoryTrueFileFalse) {
		memory = memoryTrueFileFalse;
	}
	/**The mailbox can be retrieved later by giving the username (log in)
	 * 
	 * @return
	*/
	public static boolean logIn(String username) {
		User user = getUser(username);
		if (user == null)
			return false;
		return true;
	}
	
	/**Get all messages in the system.
	 * 
	 * @return
	 */
	public static List<Message> getAllMessages() throws Exception {
		List<Message> all = new LinkedList<Message>();
		for (Map.Entry<User, MailBox> u : administrative.entrySet()) {
			try {
				if (u.getValue().updateMail() != null)
					all = Stream.concat(all.stream(), u.getValue().listMail().stream())
						.collect(Collectors.toList());
			} catch (Exception e) {}
		}
		return all;
	}

	/**Get all users in the system.
	 * 
	 * @return
	 */
	public static Set<User> getAllUsers() {return administrative.keySet();}

	/**Filter messages globally: 
	 * Get all messages in the system that fulfill a condition.
	 * 
	 * @return
	 */
	public static Stream<Message> filter(String word) throws Exception {
		return getAllMessages().stream().
			filter(str -> str.toString().contains(word));
	}

	/**Count total number of messages.
	 * 
	 * @return
	 */
	public static int countMessages() throws Exception {return getAllMessages().size(); }

	public static Map<String, List<Message>> groupBySubject() throws Exception {
		Map<String, List<Message>> subject = new HashMap<String, List<Message>>();
		List<Message> messages = getAllMessages();
		for (Message m: messages) {
			if (!subject.containsKey(m.getSubject()))
				subject.put(m.getSubject(), new LinkedList<Message>());
			subject.get(m.getSubject()).add(m);		
		}
		return subject;
	}

	//Average messages per user.
	//@betaSAV
	public static float averageMessagesPerUser() throws Exception {
		return getAllMessages().size() / (float) getAllUsers().size();
	}
	//Group messages per subject. Any user.
	public static Stream<Message> filterSubject(String word) throws Exception {
		return getAllMessages().stream().
			filter(str -> str.getSubject().contains(word));
	}

	public static Stream<Message> filterBySender(String sender, Stream<Message> m) {
		return m.filter(p -> p.getFrom().equals(sender));
	}
	public static Stream<Message> filterBySender(String sender) throws Exception {
		return getAllMessages().stream().filter(p -> p.getFrom().equals(sender));
	}
	
	//Count the words of all messages from users with a particular name.
	public static int countWordsOfMessagesFromUser(String name) {
		int sum = 0;
		for (Map.Entry<User, MailBox> m: administrative.entrySet()) {
			try {
				if (m.getKey().getName().equals(name)) {
					List<Message> message = m.getValue().listMail();
					int size = message.size();
					for (int i = 0; i < size; i ++) {
						sum += message.get(i).getBody().length();
					}
				}
			} catch (Exception e) {}
		}
		return sum;
	}

	//Get messages to users born before a certain year.
	//Receiver
	public static List<Message> usersBornBeforeXYear(int year) throws Exception {
		List<Message> list = new LinkedList<Message>();
		for (Map.Entry<User, MailBox> m: administrative.entrySet()) {
			try {
				if (m.getKey().getYear() < year) {
					list = Stream.concat(list.stream(), m.getValue()
						.listMail().stream()).collect(Collectors.toList());
				}
			} catch (Exception e) {}
		}
		return list;
	}
	//Sender
	public static List<Message> usersBornAfterXYear(int year) throws Exception {
		List<Message> list = new LinkedList<Message>();
		for (Map.Entry<User, MailBox> m: administrative.entrySet()) {
			try {
				for (Message mess: m.getValue()) {
					if (getUser(mess.getTo()).getYear() > year)
						list.add(mess);
				}
			} catch (Exception e) {}
		}
		return list;
	}
	
	public static Stream<Message> containsXWordAndLessthanNWords(List<Message> m, String word, int n) throws Exception {
		return m.stream()
			.filter(p -> p.toString().contains(word) && p.getBody().split(" ").length < n);
	}
	public static Stream<Message> containsXWord(List<Message> m, String word) throws Exception {
		return m.stream()
			.filter(p -> p.toString().contains(word));
	}
	public static Stream<Message> lessthanNWords(List<Message> m, int n) throws Exception {
		return m.stream()
			.filter(p -> p.getBody().split(" ").length < n);
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
		administrative.forEach((key,value) -> System.out.println("User: " + key));
	}

	private static boolean getExist(User u) {
		return administrative.containsKey(u);
	}
	public static boolean getExist(String username) {
		return users.get(username) != null ? true : false;
	}
	
	public static void reset() {
		administrative = new HashMap<User, MailBox>();
		users = new HashMap<String,User>();
	}


}
