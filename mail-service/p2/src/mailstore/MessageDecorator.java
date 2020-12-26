package mailstore;

import java.util.List;

import messages.Message;

public abstract class MessageDecorator implements MailStore {
	private MailStore store;

	public MessageDecorator(MailStore store) {
		this.store = store;
	}
	
	@Override
	public void sendMail(String u, Message m) {
		this.store.sendMail(u, m);

	}

	@Override
	public List<Message> getMail(String u) throws Exception {
		return this.store.getMail(u);
	}
}
