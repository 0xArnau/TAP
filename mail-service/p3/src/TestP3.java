import mailstore.MailStore;
import mailstore.OnFile;
import mailstore.RedisMailStore;
import mailstore.RedisStore;
import mailstore.StoreAdapter;
import messages.Message;

public class TestP3 {
	public static void main(String[] args) {
		
		RedisMailStore rsm = RedisStore.getInstance();
		RedisMailStore rsm1 = RedisStore.getInstance();
		MailStore ms = new OnFile();
		MailStore sa = new StoreAdapter(rsm);
		MailStore sa1 = new StoreAdapter(rsm1);

		sa.sendMail("sa", new Message("from", "to", " subject", " body"));
		try {
			sa.getMail("sa").forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("'");
		sa1.sendMail("sa", new Message("from", "to", " subject", " body"));
		try {
			sa1.getMail("sa").forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rsm.flushAll();
		rsm1.flushAll();
	}
}