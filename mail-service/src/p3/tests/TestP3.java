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
		
		RedisMailStore rsm = RedisClient.getInstance();
		RedisMailStore rsm1 = RedisClient.getInstance();
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