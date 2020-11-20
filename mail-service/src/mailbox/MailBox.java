package mailbox;

import users.*;
import messages.*;
import mailstore.*;
import system.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MailBox {
	private User user;
	private List<Message> messages;
	private boolean mem;

	public MailBox(User u, boolean mem) {
		user = u;
		this.mem = mem;
	}

	public void updateMail() throws Exception {
		if (mem)
			messages = sortByTime(MailStore.getMail(user));
		else
			messages = sortByTime(MailStoreOnFile.getMail(user));
	}

	public void listMail() {
		System.out.println(messages);
	}

	public void sentMail(User to, String subject, String body) {
		if (mem)
			MailStore.sendMail(to, new Message(user, to, subject, body));
		else
			MailStoreOnFile.sendMail(to, new Message(user, to, subject, body));
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
		String date;

		System.out.print("Word: ");
		date = reader.readLine();

		return date;
	}
	private int askDataS() throws IOException {
		BufferedReader reader =
			new BufferedReader(new InputStreamReader(System.in));
		int date;

		System.out.print("Sinteger.parse ze: ");
		date = Integer.parseInt(reader.readLine());
		return date;
	}
}