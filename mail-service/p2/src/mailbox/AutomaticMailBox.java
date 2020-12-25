package mailbox;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

	public Set<String> getSpammers() {
		Set<String>  spammers = new HashSet<String>();
		for (Observer o: list) {
			try {
				SpamUserFilter suf = ((SpamUserFilter) o);
				for (String from :suf.getMessages().stream().map(p -> p.getFrom()).collect(Collectors.toSet())) {
					spammers.add(from);
				}
			} catch (Exception e) {
				//TODO: handle exception
			}
			try {
				TooLongFilter stf = ((TooLongFilter) o);
				for (String from :stf.getMessages().stream().map(p -> p.getFrom()).collect(Collectors.toSet())) {
					spammers.add(from);
				}
			} catch (Exception e) {
				//TODO: handle exception
			}	
		}
		
		return spammers;
	}
}
