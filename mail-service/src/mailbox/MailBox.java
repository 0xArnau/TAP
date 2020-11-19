package mailbox;

import users.*;
import messages.*;
import mailstore.*;
import system.*;

import java.util.LinkedList;
import java.util.List;

public class MailBox {
	private User user;
	private List<Message> messages;

	public MailBox(User u) {
		user = u;
	}

	public void updateMail() {;}
	public void listMail() {;}
	public void getSortedMail() {;}
	public void filterMail() {;}

	private List<Message> sortByTime() {return null;}
	private List<Message> sortBySent() {return null;}
	private List<Message> sortByIDK() {return null;}
}