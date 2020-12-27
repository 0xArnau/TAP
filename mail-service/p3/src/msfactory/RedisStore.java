package msfactory;

import java.util.List;

import mailstore.MailStore;
import mailstore.RedisClient;
import messages.Message;

public class RedisStore extends RedisClient implements MailStoreFactory {

	@Override
	public MailStore createMailStore() {
		return null;
	}

	@Override
	public void sendMail(String u, Message m) {
		super.lpush(u, m.toString());
	}

	@Override
	public List<Message> getMail(String u) throws Exception {
		return super.lrange(u);
	}
	
}
