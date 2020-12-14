package mailbox;

import users.*;
import messages.*;
import mailstore.*;
//import system.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
			messages = sortByTime(store.getMail(user));
	}

	public void listMail() {
		System.out.println(messages);
	}

	public void sendMail(User to, String subject, String body) {
		store.sendMail(to, new Message(user, to, subject, body));
	}

	public void filterMail(int x) {
		switch (x) {
			case 1:
				try {
					sortByContent(askDataW());
				} catch (Exception e) {
					System.out.println("ERROR: " + e);
				}
			break;
			case 2:
				try {
					sortByLessThanN(askDataS());
				} catch (Exception e) {
					System.out.println("ERROR: " + e);
				}
			break;
			default:
				System.out.println("ERROR: number");
			break;
		}
	}

	private List<Message> sortByTime(List<Message> list) throws Exception {
		if (list == null)
			return null;

		List<Message> sorted = new LinkedList<Message>();

		while (!list.isEmpty()) {
			sorted.add(((LinkedList<Message>) list).pop());
		}
		return sorted;
	}

	private Stream<Message> sortByContent(String word) {
		return messages.stream().filter(c -> c.toString().contains(word));
	}

	private Stream<Message> sortByLessThanN(int n) {
		return messages.stream().filter(c-> c.toString().length() < n);
	}

	private String askDataW() throws IOException {
		BufferedReader reader =
			new BufferedReader(new InputStreamReader(System.in));
		//String date;

		//System.out.print("Word: ");
		//date = reader.readLine();

		return reader.readLine();
	}
	private int askDataS() throws IOException {
		BufferedReader reader =
			new BufferedReader(new InputStreamReader(System.in));
		//int date;

		//System.out.print("Sinteger.parse ze: ");
		//date = Integer.parseInt(reader.readLine());
		return Integer.parseInt(reader.readLine());
	}

	@Override
	public Iterator<Message> iterator() {
		return messages.iterator();
	}
}