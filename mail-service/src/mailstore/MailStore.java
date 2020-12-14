package mailstore;

import users.*;
import messages.*;
import java.util.List;
/**
 * MailStore
 */
public interface MailStore {

	static final String regex = ";";
	public void sendMail(User u, Message m);
	public List<Message> getMail(User u);
}

