package mailstore;


public interface RedisMailStore{

	public void lpush(String u, String m);
	public void lrange(String u);
}
