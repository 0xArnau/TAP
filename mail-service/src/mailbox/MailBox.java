package mailbox;

import messages.*;
import mailstore.*;
//import system.*;

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
		return messages.stream();
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
	
	public Stream<Message> sortMail() {
    return 	messages.stream().sorted().collect(Collectors.toList()).stream(); 
	}

	@Override
	public Iterator<Message> iterator() {
		return messages.iterator();
	}
}