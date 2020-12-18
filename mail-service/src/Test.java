import java.util.stream.Stream;

import mailbox.MailBox;
import mailstore.InMemory;
import mailstore.MailStore;
import mailstore.OnFile;
import messages.Message;
import system.MailSystem;
import users.User;

public class Test {
	public static void main(String [] args) throws Exception {
		/*
		User user = new User("star", "Arnau", 2000);
		System.out.println("\n" + user);
		User user1 = new User("Mr.Star", "Arnau", 2000);
		System.out.println("\n" + user1);
		User user2 = new User("John Wick", "Keanu Reeves", 1964);
		System.out.println("\n" + user2);
		User user3 = new User("betaSAV", "Sergi", 1999);
		System.out.println("\n" + user3);
		User user4 = new User("Mr.Jhonny", "Josep", 1985);
		System.out.println("\n" + user4);

		MailSystem.newUser(user);
		MailSystem.newUser(user1);
		MailSystem.newUser(user2);
		MailSystem.newUser(user3);
		MailSystem.newUser(user4);

		Message m1 = new Message(user, user1, "subject", "body");
		System.out.println("\n" + m1);
		Message m2 = new Message(user1, user, "subject", "body");
		System.out.println("\n" + m2);
		Message m3 = new Message(user3, user2, "Nota", "Hola profe, quina nota tindré al final del treball?");
		System.out.println("\n" + m3);
		Message m4 = new Message(user4, user, "PC", "Holaaaa, quiero montar un pc, me podrías ayudar porfa? No tengo ni idea de ordenadores. Lo harás gratis, verdad?");
		System.out.println("\n" + m4);

		System.out.println(m2.compareTo(m1));
		System.out.println(m1.compareTo(m2));
		System.out.println(m2.compareTo(m2));

		MailStore.sendMail(user, m1);
		MailStore.sendMail(user2, m3);
		MailStore.sendMail(user1, m2);
		MailStore.sendMail(user, m3);
		MailStore.sendMail(user, m4);
		System.out.println("Correu usuari 0: " + MailStore.getMail(user));
		System.out.println("Correu usuari 1: " + MailStore.getMail(user1));
		System.out.println("Correu usuari 2: " + MailStore.getMail(user2));
		
		System.out.println("\n====");
		MailStoreOnFile.sendMail(user, m2);
		MailStoreOnFile.sendMail(user, m3);
		MailStoreOnFile.sendMail(user, m3);
		System.out.println("Correu usuari 0: " + MailStoreOnFile.getMail(user));
		System.out.println("Correu usuari 1: " + MailStoreOnFile.getMail(user1));
		
		System.out.println("\n====");
		MailBox mb = new MailBox(user, true);
		MailBox mb2 = new MailBox(user, false);
		mb.updateMail();
		mb.listMail();

		System.out.println("\n====");
		mb2.updateMail();
		mb2.listMail();

		
		////
		System.out.println("\n\nMailbox Iterator:");
		for (Message m : mb) {
			System.out.println(m);
		}

		System.out.println("\nGet all users");
		System.out.println(MailSystem.getAllUsers());

		*/
		/*
		User user = new User("star", "Arnau", 2000);
		User user1 = new User("betaSAV", "Sergi", 1999);
		Message mensaje = new Message("star", "betaSAV", "yo", "WTF");
		MailStore memo = new InMemory();
		MailStore file = new OnFile();
		MailBox boxM = new MailBox("star", memo);
		MailBox boxF = new MailBox("betaSAV", file);

		
		file.sendMail("betaSAV", mensaje);
		System.out.println(file.getMail("betaSAV"));
		System.out.println("\n");
		boxF.updateMail();
		System.out.println(boxF.listMail());
		System.out.println("\n");
		for (Message m: boxF) {
			System.out.println(m);
		}
		System.out.println("\n");
		boxF.forEach(System.out::println);
		System.out.println("\n");
		boxF.filterMail("y").forEach(System.out::println);
		System.out.println("\n");
		boxF.sortMail().forEach(System.out::println);
		
		System.out.println("\n\n");
		
		System.out.println("memo");
		boxM.sendMail("star", mensaje);
		for(int i = 0; i < 100000000; ++i);
		boxM.sendMail("star", "klk", "wtf");
		for(int i = 0; i < 100000000; ++i);
		boxM.sendMail("star", "wtf", "klk");
		System.out.println("update");
		boxM.updateMail();
		System.out.println("listMail");
		System.out.println(boxM.listMail());
		System.out.println("\n");
		System.out.println("for each");
		for (Message m: boxM) {
			System.out.println(m);
		}
		System.out.println("\n");
		System.out.println("forEach");
		boxM.forEach(System.out::println);
		System.out.println("\n");
		System.out.println("filter");
		boxM.filterMail("WTF").forEach(System.out::println);
		System.out.println("\n");
		System.out.println("sort");
		boxM.sortMail().forEach(System.out::println);
		*/
		System.out.println("MailSystem");
		User star = new User("star","arnau",2000);
		User beta = new User("beta","sergi",1999);
		MailStore starMemo = new InMemory();
		MailStore betaFile = new InMemory();
		MailBox starBox = MailSystem.newUser(star, starMemo);
		MailBox betaBox = MailSystem.newUser(beta, betaFile);
		MailBox box = MailSystem.newUser(beta, betaFile);
		if (box == null)
			System.out.println("box null");
		else
			System.out.println("box not null");
		if (starMemo != null)
			System.out.println("star not null");
			if (starMemo != null)
			System.out.println("beta not null");
		MailSystem.getAllUsers().forEach(System.out::println);
		starBox.sendMail("star", "klk", "wtf");
		starBox.sendMail("beta", "klk", "wtf");
		betaBox.sendMail("beta", "wtf", "klk");
		betaBox.sendMail("star", "wtf", "klk");
		betaBox.sendMail("star", "word", "klk");
		System.out.println("star");
		starBox.updateMail().forEach(System.out::println);
		System.out.println("beta");
		betaBox.updateMail().forEach(System.out::println);
		System.out.println("all");
		MailSystem.getAllMessages().forEach(System.out::println);
		System.out.println("Filter");
		MailSystem.filter("word").forEach(System.out::println);
		System.out.println("countmessages");
		System.out.println(MailSystem.countMessages());
		System.out.println("average");
		MailSystem.averageMessagesPerUser();
	}
}
