package menu;

import mailbox.*;
import mailbox.*;
import messages.*;
import users.*;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class MailSystem {
	
	private Set<User> users= new HashSet<User>();

	private void newUser(String name, String username, int yearOfBorn){
		
		User u = new User(name,username,yearOfBorn);

		if (users.contains(u)) {
			System.out.println(">> username: " + username + " already exists.");
		} else {
			users.add(u);
		}	
	}

	/**The mailbox can be retrieved later by giving the username (log in)
	 * 
	 * @return
	*/
	private void logIn(){;}
	
	/**Get all messages in the system.
	 * 
	 * @return
	 */
	private List<Message> getAllMessages() {return null;} 

	/**Get all users in the system.
	 * 
	 * @return
	 */
	private Set<User> getAllUsers() {return null;}

	/**Filter messages globally: 
	 * Get all messages in the system that fulfill a condition.
	 * 
	 * @return
	 */
	private List<Message> filter() {return null;}

	/**Count total number of messages.
	 * 
	 * @return
	 */
	private int countMessages() {return 0;}
}
