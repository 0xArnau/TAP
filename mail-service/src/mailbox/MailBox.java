package mailbox;

import users.*;
import messages.*;
import mailstore.*;
//import system.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MailBox implements Iterable<Message> {
	private User user;
	private List<Message> messages;
	private MailStore store;

	public MailBox(User u, MailStore store) {
		user = u;
		this.store = store;
	}

	public void updateMail() throws Exception {
		
	}

	public void listMail() {
		System.out.println(messages);
	}

	public void sendMail(User to, String subject, String body) {
		store.sendMail(to, new Message(user, to, subject, body));
	}

	public void filterMail(String p) {
		messages.stream().filter(str -> str.toString().contains(p)).forEach(System.out::println);
	}

	@Override
	public Iterator<Message> iterator() {
		return messages.iterator();
	}
}