package mailstore;

public class RedisStore implements RedisMailStore {

	@Override
	public void lpush(String u, String m) {
		System.out.println("Redis");

	}

	@Override
	public void lrange(String u) {
		System.out.println("Redis");
	}
	
}
