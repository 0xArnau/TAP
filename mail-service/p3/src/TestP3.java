import mailstore.EncodeDecorator;
import mailstore.MailStore;
import mailstore.OnFile;
import mailstore.RedisMailStore;
import mailstore.RedisClient;
import mailstore.StoreAdapter;
import messages.Message;
import msfactory.MailStoreFactory;
import msfactory.StoreType;

public class TestP3 {
	public static void main(String[] args) throws Exception {
		
		RedisMailStore rsm = RedisClient.getInstance();
		RedisMailStore rsm1 = RedisClient.getInstance();
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

		MailStore file = MailStoreFactory.createMailStore(StoreType.FILE);
		((EncodeDecorator) file).setCipher();
		System.out.println("File");
		file.sendMail("star",new Message("star","star","klk","1234567878990"));
		file.getMail("star").forEach(System.out::println);
		MailStore redis = MailStoreFactory.createMailStore(StoreType.REDIS);
		System.out.println("Redis");
		redis.sendMail("star",new Message("star","star","qwe","123213213"));
		redis.getMail("star").forEach(System.out::println);
		MailStore memo = MailStoreFactory.createMailStore(StoreType.MEMORY);
		System.out.println("Mem");
		memo.sendMail("star",new Message("star","star", "subject"," body"));
		memo.getMail("star").forEach(System.out::println);
	}
}