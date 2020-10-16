package mailstore;

import users.*;
import messages.*;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;

public class MailStore {
	private Map<String, List<Message>> messages = new HashMap<String, List<Message>>();

	public MailStore() {

	}

	/**
	 * 
	 * @param u
	 * @param m
	 */
	public void sendMail(User u, Message m) {
		if (messages.containsKey(u.getUserName())) {
			messages.get(u.getUserName()).add(m);
			return;
		}
		messages.put(u.getUserName(),new LinkedList<Message>(){{add(m);}});
	}
	/**
	 * 
	 * @param u
	 * @return
	 */
	public List getMail(User u) {
		return messages.get(u.getUserName());
	}
}