package mailstore;

import users.*;
import messages.*;


import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class MailStore {
	protected static String regex = ";";
	private static Map<User, List<Message>> messages = new HashMap<User, List<Message>>();
	/**
	 * 
	 * @param u
	 * @param m
	 */
	public static void sendMail(User u, Message m) {
		if (messages.containsKey(u)) {
			if (m.toString().matches(".+;"+u.getUserName()+";.+"))
				messages.get(u).add(m);
			else
				System.out.println("ERROR ==> Message(to) != User");
			return;
		}
		messages.put(u,new LinkedList<Message>(){{add(m);}});
	}
	/**
	 * 
	 * @param u
	 * @return
	 */
	public static List<Message> getMail(User u) {
		return new LinkedList<Message>(messages.get(u));
	}
}