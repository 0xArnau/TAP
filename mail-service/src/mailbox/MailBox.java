package mailbox;

import messages.*;
import mailstore.*;
//import system.*;

import java.util.Iterator;
import java.util.List;


public class MailBox implements Iterable<Message> {
	private String user;
	private List<Message> messages;
	private MailStore store;

	public MailBox(String u, MailStore store) {
		user = u;
		this.store = store;
	}

	public void updateMail() throws Exception {
		messages = store.getMail(user);
	}

	public List<Message> listMail() {
		return messages;
	}

	public void sendMail(String to, String subject, String body) {
		store.sendMail(to, new Message(user, to, subject, body));
	}

	public void filterMail(String p) {
		messages.stream().filter(str -> str.toString().contains(p)).forEach(System.out::println);
	}
	
	public void getMail() {}

	@Override
	public Iterator<Message> iterator() {
		return messages.iterator();
	}
}