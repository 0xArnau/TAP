package mailbox;


import mailstore.*;
import patterns.*;

public class AutomaticMailBox extends MailBox implements Subject{
	public AutomaticMailBox (String u, MailStore store) {
		super(u, store);
	}

	@Override
	public void attach(Observer o) {

	}
	@Override
	public void detach(Observer o) {

	}
	@Override
	public void notifyObservers() {

	}
}
