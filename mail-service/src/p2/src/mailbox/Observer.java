package p2.src.mailbox;

import java.util.List;

import p1.src.messages.Message;

public interface Observer {
	public void update(List<Message> m);
}
