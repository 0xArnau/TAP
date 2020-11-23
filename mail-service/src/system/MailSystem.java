package system;

import mailbox.*;
import mailstore.*;
import messages.*;
import users.*;

import java.util.Set;
//import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class MailSystem {
	
	//DB?
	private static Map<String, User> users= new HashMap<String, User>();
	private static boolean inMem = true;
	private static MailBox box;

	public static void newUser(String name, String username, int yearOfBorn){
		
		User u = new User(username,name,yearOfBorn);
		if (getExist(u.getUserName())) {
			System.out.println("=> username: " + username + " already exists.");
		} else {
			users.put(username,u);
			System.out.println("=> username: " + username + " created.");
		}	
	}
	public static void newUser(User u){

		if (getExist(u.getUserName())) {
			System.out.println("=> username: " + u.getUserName() + " already exists.");
		} else {
			users.put(u.getUserName(), u);
			System.out.println("=> username: " + u.getUserName() + " created.");
		}	
	}

	/**The mailbox can be retrieved later by giving the username (log in)
	 * 
	 * @return
	*/
	public void logIn(){;}
	
	/**Get all messages in the system.
	 * 
	 * @return
	 */
	public List<Message> getAllMessages() {return null;} 

	/**Get all users in the system.
	 * 
	 * @return
	 */
	public Set<User> getAllUsers() {return null;}

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

	public static void printUsers() {
		users.forEach((key,value) -> System.out.println("User: " + value));
	}

	public static boolean getExist(String username) {
		return users.containsKey(username);
	}

	public static void inMem(boolean b) {
		inMem = b;
	}

	public static void createMailBox(User u) {
		box = new MailBox(u, inMem);
	}
}
