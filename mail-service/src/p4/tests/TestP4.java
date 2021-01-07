package p4.tests;

import p4.dynamic.Log;
import p4.system.MailSystem;

import p1.mailbox.MailBox;
import p1.messages.Message;
import p1.users.User;

public class TestP4 {
	public static void main(String[] args) throws Exception {
		MailSystem mailSystemTest = new MailSystem();
		MailBox mailBoxTest = mailSystemTest.newUser(new User("Mr.Star", "arnau", 2000));
		System.out.println("Enviando correo...");
		mailBoxTest.sendMail("Mr.Star", new Message("Mr.Star", "Mr.Star", "Correo de Pruebas",
				"Hola, yo mismo. Espero que estés recibiendo este correo. TKM."));
		System.out.println("Actualizando correos...");
		mailBoxTest.updateMail().forEach(System.out::println);
		System.out.println("Printando log: ");
		//Log.getLogs().forEach(System.out::println);
	}
}
