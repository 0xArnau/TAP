



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
	}
}
