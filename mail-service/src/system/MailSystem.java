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
	private static List<Message> allMessages = new LinkedList<Message>();
	public static MailBox newUser(User u, MailStore store){
		if (getExist(u)) {
			System.out.println("=> username: " + u.getUserName() + " already exists.");
			u = null;
			return null;
		} else {
			MailBox box = new MailBox(u.getUserName(), store);
			administrative.put(u,box);
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
	public List<Message> getAllMessages() {
		/*List<Message> all = new LinkedList<Message>();
		for (Map.Entry<User, MailBox> u : administrative.entrySet()) {
			all = Stream.concat(all.stream(), u.getValue().listMail().stream())
				.collect(Collectors.toList());
		}*/
		return allMessages;
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
	public List<Message> filter() {
		
		return null;
	}

	/**Count total number of messages.
	 * 
	 * @return
	 */
	public int countMessages() {return 0;}

	//Average messages per user.

	//Group messages per subject. Any user.

	//Count the words of all messages from users with a particular name.

	//Get messages to users born before a certain year.

	public static void removeUser(String username) {
		User rm = getUser(username);
		if (rm != null) administrative.remove(rm);
	}
	public static void removeUser(User u) {
		administrative.remove(u);
	}

	public static User getUser(String username) {
		for (User u : administrative.keySet()) {
				if (u.getUserName().equals(username)) return u;
		}
		return null;
	}

	public static void printUsers() {
		administrative.forEach((key,value) -> System.out.println("User: " + key));
	}

	public static boolean getExist(User u) {
		return administrative.containsKey(u);
	}
	public static boolean getExist(String username) {
		for (User u : administrative.keySet()) {
			if (u.getUserName().equals(username)) return true;
		}
		return false;
	}



}
