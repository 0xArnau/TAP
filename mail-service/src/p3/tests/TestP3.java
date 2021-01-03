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
		storeAdapter.sendMail("Mr.User", new Message("Mr.Sender", "Mr.User", " Feliz año nuevo", "Buenas!!Espero que todo te vaya bien, te envio este correo para desearte lo mejor en este 2021."));
		System.out.println("Correos de Mr.User: ");
		try {
			storeAdapter.getMail("Mr.User").forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Enviando mensaje storeAdapter1...");
		storeAdapter1.sendMail("Mr.User", new Message("Mr.Sender", "Mr.User", "Feliz año nuevo", "Buenas!!Espero que todo te vaya bien, te envio este correo para desearte lo mejor en este 2021."));
		System.out.println("Correos de Mr.User: ");
		try {
			storeAdapter1.getMail("Mr.User").forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		redisMailStore0.flushAll();
		redisMailStore1.flushAll();
		
		MailStoreFactory mailStoreFactoryTest = new FileFactory();
		MailStore file = mailStoreFactoryTest.createMailStore();
		((EncodeDecorator) file).setCipher();
		System.out.println("Archivo");
		file.sendMail("star",new Message("star","star","Prueba n.1","Feliz"));
		file.getMail("star").forEach(System.out::println);
		mailStoreFactoryTest = new RedisFactory();
		MailStore redis = mailStoreFactoryTest.createMailStore();
		System.out.println("Redis");
		redis.sendMail("star",new Message("star","star","Prueba n.2","año"));
		redis.getMail("star").forEach(System.out::println);
		mailStoreFactoryTest = new MemoryFactory();
		MailStore memo = mailStoreFactoryTest.createMailStore();
		System.out.println("Memoria");
		memo.sendMail("star",new Message("star","star", "Prueba n.3","nuevo"));
		memo.getMail("star").forEach(System.out::println);
		
		MailSystemFactory mailSystemFactoryTest = new MailSystemFactory(new FileFactory());
		mailSystemFactoryTest.newUser(new User("star","arnau",2000));
		mailSystemFactoryTest.getAllUsers().forEach(System.out::println);
		mailSystemFactoryTest.updateMessages();
		mailSystemFactoryTest.getAllMessages().forEach(System.out::println);
	}
}