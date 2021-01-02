package p3.tests;

import p2.mailstore.EncodeDecorator;
import p1.mailstore.MailStore;
import p3.mailstore.RedisMailStore;
import p3.mailstore.RedisClient;
import p3.mailstore.StoreAdapter;
import p1.messages.Message;
import p3.msfactory.FileFactory;
import p3.msfactory.MailStoreFactory;
import p3.msfactory.MemoryFactory;
import p3.msfactory.RedisFactory;
import p3.system.MailSystemFactory;
import p1.users.User;


public class TestP3 {
	public static void main(String[] args) throws Exception {
		
		RedisMailStore redisMailStore0 = RedisClient.getInstance();
		RedisMailStore redisMailStore1 = RedisClient.getInstance();
		MailStore storeAdapter = new StoreAdapter(redisMailStore0);
		MailStore storeAdapter1 = new StoreAdapter(redisMailStore1);

		System.out.println("Enviando mensaje storeAdapter...");
		storeAdapter.sendMail("Mr.User", new Message("from", "to", " subject", " body"));
		System.out.println("Correos de Mr.User: ");
		try {
			storeAdapter.getMail("Mr.User").forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Enviando mensaje storeAdapter1...");
		storeAdapter1.sendMail("Mr.User", new Message("from", "to", " subject", " body"));
		System.out.println("Correos de Mr.User: ");
		try {
			storeAdapter1.getMail("Mr.User").forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		redisMailStore0.flushAll();
		redisMailStore1.flushAll();
		
		MailStoreFactory msf = new FileFactory();
		MailStore file = msf.createMailStore();
		((EncodeDecorator) file).setCipher();
		System.out.println("File");
		file.sendMail("star",new Message("star","star","klk","1234567878990"));
		file.getMail("star").forEach(System.out::println);
		msf = new RedisFactory();
		MailStore redis = msf.createMailStore();
		System.out.println("Redis");
		redis.sendMail("star",new Message("star","star","qwe","123213213"));
		redis.getMail("star").forEach(System.out::println);
		msf = new MemoryFactory();
		MailStore memo = msf.createMailStore();
		System.out.println("Mem");
		memo.sendMail("star",new Message("star","star", "subject"," body"));
		memo.getMail("star").forEach(System.out::println);
		
		MailSystemFactory misf = new MailSystemFactory(new FileFactory());
		misf.newUser(new User("star","arnau",2000));
		misf.getAllUsers().forEach(System.out::println);
		misf.updateMessages();
		misf.getAllMessages().forEach(System.out::println);
	}
}