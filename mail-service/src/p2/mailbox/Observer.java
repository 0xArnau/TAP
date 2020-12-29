package p2.mailbox;

import java.util.List;

import p1.messages.Message;

public interface Observer {
	public void update(List<Message> m);
}
