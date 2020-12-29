package p3.mailstore;

import java.util.List;

import p1.messages.Message;
import p1.mailstore.*;


public class StoreAdapter implements MailStore {

	private RedisMailStore redis = null;

	public StoreAdapter(RedisMailStore redis) {
		this.redis = redis;
	}

	@Override
	public void sendMail(String u, Message m) {
		redis.lpush(u, m.toString());
	}

	@Override
	public List<Message> getMail(String u) throws Exception {
		return redis.lrange(u);
	}
}
