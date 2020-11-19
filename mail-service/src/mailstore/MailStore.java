package mailstore;

import users.*;
import messages.*;


import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;

public class MailStore {
	protected String regex = ";";
	private Map<User, List<Message>> messages = new HashMap<User, List<Message>>();
	/**
	 * 
	 * @param u
	 * @param m
	 */
	public void sendMail(User u, Message m) {
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
	public List<Message> getMail(User u) {
		return messages.get(u);
	}
}