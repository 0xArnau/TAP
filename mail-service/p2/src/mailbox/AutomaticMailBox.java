package mailbox;

import java.util.Observer;

import mailstore.*;
import patterns.*;

public class AutomaticMailBox extends MailBox{
	public AutomaticMailBox (String u, MailStore store) {
		super(u, store);
	}	
}
