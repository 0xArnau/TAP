package p3.mailstore;

import java.util.LinkedList;
import java.util.List;

import p1.messages.Message;

public class RedisClient implements RedisMailStore {

	private static RedisClient instance = null;

	public RedisClient() {
	}

	public static RedisClient getInstance() {
		if (RedisClient.instance == null)
			RedisClient.instance = new RedisClient();
		return RedisClient.instance;
	}

	@Override
	public void lpush(String u, String m) {
		jedis.lpush(u, m);
	}

	@Override
	public List<Message> lrange(String u) {
		List<Message> list = new LinkedList<Message>();
		jedis.lrange(u, 0, -1).forEach( (e) -> {
			list.add(new Message(e));
		});
		return list;
	}

	@Override
	public void flushAll() {
		jedis.flushAll();
	}
}
