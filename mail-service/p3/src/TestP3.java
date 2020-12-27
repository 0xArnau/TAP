import mailstore.MailStore;
import mailstore.OnFile;
import mailstore.RedisMailStore;
import mailstore.RedisStore;
import mailstore.StoreAdapter;
import messages.Message;
import redis.clients.jedis.Jedis;

public class TestP3 {
	public static void main(String[] args) {
		
		RedisMailStore rsm = new RedisStore();
		MailStore ms = new OnFile();
		MailStore sa = new StoreAdapter(rsm);

		sa.sendMail("sa", new Message("from", "to", " subject", " body"));
		try {
			sa.getMail("sa").forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rsm.flushAll();
	}
}