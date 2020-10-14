package mailStore;

import users.*;
import messages.*;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;

//static ?
public class MailStore {
	private Map<String, List<Message>> messages = new HashMap<String, List<Message>>();

	/**
	 * 
	 * @param u
	 * @param m
	 */
	public void sendMail(User u, Message m) {
		
	}

	public List getMail(User u) {
		return messages.get(u.getUserName());
	}


}