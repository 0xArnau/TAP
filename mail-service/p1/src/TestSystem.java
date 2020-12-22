import java.util.List;
import java.util.Map;
import java.util.Map.*;

import mailbox.MailBox;
import mailstore.InMemory;
import mailstore.MailStore;
import mailstore.OnFile;
import messages.Message;
import system.MailSystem;
import users.User;

public class TestSystem {
    public static void main(String[] args) throws Exception {
        
        MailSystem system = new MailSystem();
        MailStore starMem = new InMemory();
        MailStore mrStarMem = new InMemory();
        MailStore johnMem = new InMemory();
        MailStore betaMem = new InMemory();
        MailStore jhonnyMem = new InMemory();
		
        // Test paso 1.1
        // Test paso 1.2
        System.out.println("1.1/2");
        System.out.println("--IN-MEMORY IMPLEMENTATION--");
        System.out.println("Creando cuentas...");
        User user = new User("star", "Arnau", 2000);
        User user1 = new User("Mr.Star", "Arnau", 2001);
        User user2 = new User("John Wick", "Keanu Reeves", 1964);
        User user3 = new User("betaSAV", "Sergi", 1999);
        User user4 = new User("Mr.Jhonny", "Josep", 1985);

        MailBox starBox = system.newUser(user, starMem);
        MailBox mrStarBox = system.newUser(user1, mrStarMem);
        MailBox johnBox = system.newUser(user2, johnMem);
        MailBox betaBox = system.newUser(user3, betaMem);
        MailBox jhonnyBox = system.newUser(user4, jhonnyMem);

        // Test paso 1.3
        System.out.println("1.3");
        System.out.println("Enviando correos...");
        starBox.sendMail(user1.getUserName(), "subject", "body");
        mrStarBox.sendMail(user.getUserName(), "subject", "body");
        betaBox.sendMail(user2.getUserName(), "Nota", "Hola profe, quina nota tindré al final del treball?");
        jhonnyBox.sendMail(user.getUserName(), "PC",
                "Holaaaa, quiero montar un pc, me podrías ayudar porfa? No tengo ni idea de ordenadores. Lo harás gratis, verdad?");
        
        system.updateMessages();
        System.out.println("Actualizando correos...");
        system.getAllMessages();
        // Test paso 1.4
        // Test paso 1.5
        System.out.println("1.4/5");
        System.out.println("Actualizando correo star...");
        starBox.updateMail().forEach(System.out::println);

        // Test paso 1.6 AHORA FUNCIONA PERO LISTMAIL PETA SI NO HAY CORREOS, UPDATEMAIL
        // ES REQUERIDO ANTES DE LISTMAIL
        System.out.println("1.6");
        starBox.sortMailBySender().forEach(System.out::println);

        // Test paso 1.7
        // Test paso 1.8
        System.out.println("1.7/8");
        System.out.println("Correos con la palabra subject y del usuario star");
        system.filterBySender("star", system.filterSubject("subject")).forEach(System.out::println);

        // Test paso 1.9
        System.out.println("1.9");
        system.subjectSingleWord(system.usersBornAfterXYear(2000)).forEach(System.out::println);

        // Test paso 1.10
        System.out.println("1.10");
        System.out.println("Mensajes en el sistema: " + system.countMessages());

        // Test paso 1.11
        System.out.println("1.11");
        System.out.println("Mensajes de media por usuario: ");
        system.averageMessagesPerUser();

        // Test paso 1.12
        System.out.println("1.12");
        Map<String, List<Message>> s = system.groupBySubject();
        for (Entry<String, List<Message>> m : s.entrySet()) {
            m.getValue().forEach(System.out::println);
        }

        // Test paso 1.13
        // Test paso 1.14
        System.out.println("1.13/14");
        System.out.println("Palabras totales enviadas por Arnau: " + system.countWordsOfMessagesFromUser("Arnau"));

        // Test paso 1.15
        System.out.println("1.15");
        System.out.println("Correos enviados por usuarios nacidos antes del 2000: ");
        system.usersBornBeforeXYear(2000).forEach(System.out::println);

        system.reset();

        // Test paso 1.16
        System.out.println("2.1");
        System.out.println("1.16");
        starMem = new OnFile();
        mrStarMem = new OnFile();
        johnMem = new OnFile();
        betaMem = new OnFile();
        jhonnyMem = new OnFile();

        // Test paso 2.2
        System.out.println("2.2");
        System.out.println("--FILE IMPLEMENTATION--");
        System.out.println("Creando cuentas...");
        user = new User("star", "Arnau", 2000);
        user1 = new User("Mr.Star", "Arnau", 2001);
        user2 = new User("John Wick", "Keanu Reeves", 1964);
        user3 = new User("betaSAV", "Sergi", 1999);
        user4 = new User("Mr.Jhonny", "Josep", 1985);

        starBox = system.newUser(user, starMem);
        mrStarBox = system.newUser(user1, mrStarMem);
        johnBox = system.newUser(user2, johnMem);
        betaBox = system.newUser(user3, betaMem);
        jhonnyBox = system.newUser(user4, jhonnyMem);

        // Test paso 2.3
        System.out.println("2.3");
        System.out.println("Enviando correos...");
        starBox.sendMail(user1.getUserName(), "subject", "body");
        mrStarBox.sendMail(user.getUserName(), "subject", "body");
        betaBox.sendMail(user2.getUserName(), "Nota", "Hola profe, quina nota tindré al final del treball?");
        jhonnyBox.sendMail(user.getUserName(), "PC",
                "Holaaaa, quiero montar un pc, me podrías ayudar porfa? No tengo ni idea de ordenadores. Lo harás gratis, verdad?");

        system.updateMessages();
        System.out.println("Actualizando correos...");
        system.getAllMessages();
        // Test paso 2.4
        // Test paso 2.5
        System.out.println("2.4/5");
        System.out.println("Actualizando correo star...");
        starBox.updateMail().forEach(System.out::println);

        // Test paso 2.6 A HACER
        System.out.println("2.6");
        starBox.sortMailBySender().forEach(System.out::println);

        // Test paso 2.7
        // Test paso 2.8
        System.out.println("2.7/8");
        System.out.println("Correos con la palabra pc y del usuario star");
        system.filterBySender("star", system.filterSubject("subject")).forEach(System.out::println);
        system.filterBySender("star").forEach(System.out::println);
        system.filterSubject("subjecy").forEach(System.out::println);

        // Test paso 2.9 A HACER
        System.out.println("2.9");
        system.subjectSingleWord(system.usersBornAfterXYear(2000)).forEach(System.out::println);

        // Test paso 2.10
        System.out.println("2.10");
        System.out.println("Mensajes en el sistema: " + system.countMessages());

        // Test paso 2.11
        System.out.println("2.11");
        System.out.println("Mensajes de media por usuario: ");
        system.averageMessagesPerUser();

        // Test paso 2.12 A HACER
        System.out.println("2.12");
        Map<String, List<Message>> g = system.groupBySubject();
        for (Entry<String, List<Message>> m : g.entrySet()) {
            m.getValue().forEach(System.out::println);
        }

        // Test paso 2.13
        // Test paso 2.14
        System.out.println("2.13/14");
        System.out.println("Palabras totales enviadas por Arnau: " + system.countWordsOfMessagesFromUser("Arnau"));

        // Test paso 2.15
        System.out.println("2.15");
        System.out.println("Correos enviados por usuarios nacidos antes del 2000: ");
        system.usersBornBeforeXYear(2000).forEach(System.out::println);
        
        System.out.println("\n<=>");
    }
}
