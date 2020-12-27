package mailstore;

import java.util.List;

import messages.Message;

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
		redis.lrange(u);
		return null;
	}
	
}
