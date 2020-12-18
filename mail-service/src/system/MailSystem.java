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

	public static MailBox newUser(User u, MailStore store){
		if (getExist(u)) {
			System.out.println("=> username: " + u.getUserName() + " already exists.");
			u = null;
			return null;
		} else {
			MailBox box = new MailBox(u.getUserName(), store);
			administrative.put(u,box);
			users.put(u.getUserName(), u);
			System.out.println("=> user: " + u.getUserName() + " created.");
			return box;
		}
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
			if (u.getValue().updateMail() != null)
				all = Stream.concat(all.stream(), u.getValue().listMail().stream())
					.collect(Collectors.toList());
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

	//Average messages per user.
	//@betaSAV
	public static void averageMessagesPerUser() {
		for (Map.Entry<User,MailBox> m: administrative.entrySet()) {
			System.out.println("user: " + m.getKey().getUserName() + " average messages: " + m.getValue().listMail().size());
		}
	}
	//Group messages per subject. Any user.
	public static Stream<Message> filterSubject(String word) throws Exception {
		return getAllMessages().stream().
			filter(str -> str.getSubject().contains(word));
	}

	//Count the words of all messages from users with a particular name.
	public static int countWordsOfMessagesFromUser(String name) {
		int sum = 0;
		for (Map.Entry<User, MailBox> m: administrative.entrySet()) {
			if (m.getKey().getName().equals(name)) {
				List<Message> message = m.getValue().listMail();
				int size = message.size();
				for (int i = 0; i < size; i ++) {
					sum += message.get(i).getBody().length();
				}
			}
		}
		return sum;
	}

	//Get messages to users born before a certain year.
	public static List<Message> usersBornBeforeXYear(int year) throws Exception {
		List<Message> list = new LinkedList<Message>();
		for (Map.Entry<User, MailBox> m: administrative.entrySet()) {
			if (m.getKey().getYear() < year) {
				list = Stream.concat(list.stream(), m.getValue().listMail().stream())
					.collect(Collectors.toList());
			}
		}
		return list;
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



}
