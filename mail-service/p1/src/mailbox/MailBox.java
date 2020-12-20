package mailbox;

import messages.*;
import mailstore.*;
//import system.*;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MailBox implements Iterable<Message> {
	private String user;
	private List<Message> messages;
	private MailStore store;

	public MailBox(String u, MailStore store) {
		user = u;
		this.store = store;
	}

	public Stream<Message> updateMail() throws Exception {
		messages = store.getMail(user);
		if (messages == null)
			return null;
		return messages.stream();
	}

	public List<Message> listMail() {
		return messages;
	}

	public void sendMail(String to, String subject, String body) {
		store.sendMail(to, new Message(user, to, subject, body));
	}

	public void sendMail(String to, Message m) {
		store.sendMail(to, m);
	}

	public Stream<Message> filterMail(String p) {
		return messages.stream().filter(str -> str.toString().contains(p));
	}

	public Stream<Message> sortMail() {
		return messages.stream().sorted().collect(Collectors.toList()).stream(); // sorted(Comparator.reverseOrder())
																					// sorted()
	}

	public List<Message> sortMailBySender() {
		return messages.stream().sorted(Comparator.comparing(Message::getFrom)).collect(Collectors.toList());
	}

	@Override
	public Iterator<Message> iterator() {
		if (messages == null)
			return null;
		return messages.iterator();
	}

	public Stream<Message> filterSubject(String word) throws Exception {
		return messages.stream().filter(str -> str.getSubject().contains(word));
	}
}