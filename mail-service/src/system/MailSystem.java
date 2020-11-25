package system;

import mailbox.*;
import mailstore.*;
import messages.*;
import users.*;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
//import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class MailSystem {
	//DB?
	private static Map<String, User> users= new HashMap<String, User>();
	private static User user;

	public static void newUser(String name, String username, int yearOfBorn){
		
		User u = new User(username,name,yearOfBorn);
		if (getExist(u.getUserName())) {
			System.out.println("=> username: " + username + " already exists.");
		} else if (username.equalsIgnoreCase("exit")) {
			System.out.println("ERROR: invalid username: " + username);
		} else {
			users.put(username,u);
			System.out.println("=> username: " + username + " created.");
		}	
	}
	public static void newUser(User u){

		if (getExist(u.getUserName())) {
			System.out.println("=> username: " + u.getUserName() + " already exists.");
		} else if (u.getUserName().equalsIgnoreCase("exit")) {
			System.out.println("ERROR: invalid username: " + u.getUserName());
		} else {
			users.put(u.getUserName(), u);
			System.out.println("=> username: " + u.getUserName() + " created.");
		}	
	}

	/**The mailbox can be retrieved later by giving the username (log in)
	 * 
	 * @return
	*/
	public static boolean logIn(String username) {
		user = getUser(username);
		if (user == null)
			return false;
		return true;
	}
	
	/**Get all messages in the system.
	 * 
	 * @return
	 */
	public List<Message> getAllMessages() {
		
		

		return null;
	} 

	/**Get all users in the system.
	 * 
	 * @return
	 */
	public static Set<User> getAllUsers() {return users.values().stream().collect(Collectors.toSet());}

	/**Filter messages globally: 
	 * Get all messages in the system that fulfill a condition.
	 * 
	 * @return
	 */
	public List<Message> filter() {return null;}

	/**Count total number of messages.
	 * 
	 * @return
	 */
	public int countMessages() {return 0;}

	public static User getUser(String username) {
		return users.get(username);
	}
	public static User getCurrentUser() {
		return user;
	}

	public static void printUsers() {
		users.forEach((key,value) -> System.out.println("User: " + value));
	}

	public static boolean getExist(String username) {
		return users.containsKey(username);
	}



}
