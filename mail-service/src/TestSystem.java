import java.util.stream.Stream;

import mailbox.MailBox;
import mailstore.InMemory;
import mailstore.MailStore;
import mailstore.OnFile;
import messages.Message;
import system.MailSystem;
import users.User;

public class TestSystem {
    public static void main(String [] args) throws Exception {
        //Test paso 1.1
        //Test paso 1.2
        System.out.println("--IN-MEMORY IMPLEMENTATION--");
	    User user = new User("star", "Arnau", 2000);
		User user1 = new User("Mr.Star", "Arnau", 2000);
		User user2 = new User("John Wick", "Keanu Reeves", 1964);
		User user3 = new User("betaSAV", "Sergi", 1999);
		User user4 = new User("Mr.Jhonny", "Josep", 1985);
		MailStore star = new InMemory();
		MailStore mrStar = new InMemory();
		MailStore john = new InMemory();
		MailStore beta = new InMemory();
		MailStore jhonny = new InMemory();
		MailBox starBox = MailSystem.newUser(user, star);
		MailBox mrStarBox = MailSystem.newUser(user1, mrStar);
		MailBox johnBox = MailSystem.newUser(user2, john);
		MailBox betaBox = MailSystem.newUser(user3, beta);
        MailBox jhonnyBox = MailSystem.newUser(user4, jhonny);
        
        //Test paso 1.3
        starBox.sendMail(user1.getUserName(), "subject", "body");
        mrStarBox.sendMail(user.getUserName(), "subject", "body");
        betaBox.sendMail(user2.getUserName(), "Nota", "Hola profe, quina nota tindré al final del treball?");
        jhonnyBox.sendMail(user.getUserName(), "PC", "Holaaaa, quiero montar un pc, me podrías ayudar porfa? No tengo ni idea de ordenadores. Lo harás gratis, verdad?");

        //Test paso 1.4
        //Test paso 1.5
        starBox.updateMail().forEach(System.out::println);

        //Test paso 1.6 A HACER


        //Test paso 1.7
        //Test paso 1.8
        starBox.filterSubject("Nota").forEach(System.out::println);

        //Test paso 1.9 A HACER
        //MailSystem.filterSubject("").forEach(System.out::println);

        //Test paso 1.10
        System.out.println(MailSystem.countMessages());

        //Test paso 1.11
        MailSystem.averageMessagesPerUser();

        //Test paso 1.12 A HACER

        //Test paso 1.13
        //Test paso 1.14
        System.out.println(MailSystem.countWordsOfMessagesFromUser("Arnau"));

        //Test paso 1.15
        MailSystem.usersBornBeforeXYear(2000).forEach(System.out::println);
        System.out.println("--FILE IMPLEMENTATION--");

        //Test paso 1.16
        //Test paso 2.2
        user = new User("star1", "Arnau", 2000);
		user1 = new User("Mr.Star1", "Arnau", 2000);
		user2 = new User("John Wick1", "Keanu Reeves", 1964);
		user3 = new User("betaSAV1", "Sergi", 1999);
		user4 = new User("Mr.Jhonny1", "Josep", 1985);

        star = new OnFile();
		mrStar = new OnFile();
		john = new OnFile();
		beta = new OnFile();
        jhonny = new OnFile();
        
        starBox = MailSystem.newUser(user, star);
		mrStarBox = MailSystem.newUser(user1, mrStar);
		johnBox = MailSystem.newUser(user2, john);
		betaBox = MailSystem.newUser(user3, beta);
        jhonnyBox = MailSystem.newUser(user4, jhonny);
        //Test paso 2.3
        starBox.sendMail(user1.getUserName(), "subject", "body");
        mrStarBox.sendMail(user.getUserName(), "subject", "body");
        betaBox.sendMail(user2.getUserName(), "Nota", "Hola profe, quina nota tindré al final del treball?");
        jhonnyBox.sendMail(user.getUserName(), "PC", "Holaaaa, quiero montar un pc, me podrías ayudar porfa? No tengo ni idea de ordenadores. Lo harás gratis, verdad?");

        //Test paso 2.4
        //Test paso 2.5
        starBox.updateMail().forEach(System.out::println);

        //Test paso 2.6 A HACER


        //Test paso 2.7
        //Test paso 2.8
        starBox.filterSubject("Nota").forEach(System.out::println);

        //Test paso 2.9 A HACER
        //MailSystem.filterSubject("").forEach(System.out::println);

        //Test paso 2.10
        System.out.println(MailSystem.countMessages());

        //Test paso 2.11
        MailSystem.averageMessagesPerUser();

        //Test paso 2.12 A HACER

        //Test paso 2.13
        //Test paso 2.14
        System.out.println(MailSystem.countWordsOfMessagesFromUser("Arnau"));

        //Test paso 2.15
        MailSystem.usersBornBeforeXYear(2000).forEach(System.out::println);

    }
}
