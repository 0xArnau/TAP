package mailstore;

import users.*;
import messages.*;
import java.util.List;
/**
 * MailStore
 */
public interface MailStore {

	final String regex = ";";
	public static void sendMail(User u, Message m) {};
	public static List<Message> getMail(User u)  {return null;};
}

