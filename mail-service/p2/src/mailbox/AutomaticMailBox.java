package mailbox;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import mailstore.*;
import messages.Message;
import patterns.*;

public class AutomaticMailBox extends MailBox implements Subject{
	private List<Observer> list = new LinkedList<Observer>();
	
	public AutomaticMailBox (String u, MailStore store) {
		super(u, store);
	}

	//<Observer>
	@Override
	public void attach(Observer o) {
		list.add(o);
	}
	@Override
	public void detach(Observer o) {
		list.remove(o);
	}
	@Override
	public void notifyObservers() {
		for (Observer o: list) {
			o.update(super.listMail());
		}
	}

	//</Observer>

	@Override
	public Stream<Message> updateMail() throws Exception {
		super.updateMail();
		notifyObservers();
		return super.listMail().stream();
	}

}
