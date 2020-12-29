package p2.src.tests;

import java.util.HashSet;
import java.util.Set;

import p2.src.mailbox.AutomaticMailBox;
import p1.src.mailbox.MailBox;
import p2.src.mailbox.SpamUserFilter;
import p2.src.mailbox.TooLongFilter;
import p1.src.mailstore.InMemory;
import p1.src.mailstore.MailStore;
import p1.src.users.User;

@SuppressWarnings("unused")
public class TestObserver {
	public static void main(String[] args) {
		User star = new User("star", "arnau", 2000);
		User spam = new User("Mr.Spammer", "Carlos", 2000);
		User Mrlimpio = new User("Mr.Limpio", "Mike", 1978);
		MailStore ms = new InMemory();
		MailBox mb = new MailBox("star", ms);
		MailBox mbsp = new MailBox("Mr.spammer", ms);
		MailBox mrsp = new MailBox("Mr.Limpio", ms);

		System.out.println("Enviando correos...");
		mb.sendMail("Mr.Limpio", "Hola", "Cómo va la vida?");
		mbsp.sendMail("star", "Usted es un ganador!", "Señor, le informamos de que usted ha ganado un magnífico premio de 10000000000€, para reclamarlo solo debe pasarnos sus datos de la targeta bancaria y en menos de 5 minutos tendrá el dinero ingresado.");
		mbsp.sendMail("star", "spam", "Suscribete ya a nuestro nuevo servicio de depilación masculina!");
		mb.sendMail("Mr.spammer", "Hola", "Adios");
		mrsp.sendMail("star", "Hola", "Very good ty, you?");

		try {
			mb.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			mbsp.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			mrsp.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nCreando AutomaticMailBox, TooLongFilter, SpamUserFilter...");
		AutomaticMailBox ambstar = new AutomaticMailBox("star", ms);
		AutomaticMailBox ambspam = new AutomaticMailBox("spam", ms);
		AutomaticMailBox amblimpio = new AutomaticMailBox("Mr.Limpio", ms);
		TooLongFilter t = new TooLongFilter();
		SpamUserFilter s = new SpamUserFilter();
		ambstar.attach(t);
		ambstar.attach(s);
		ambspam.attach(t);
		ambspam.attach(s);
		amblimpio.attach(t);
		amblimpio.attach(s);

		System.out.println("Lista de correos con filtros aplicados: ");
		try {
			ambstar.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ambspam.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			amblimpio.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Set<String> st = ambstar.getSpammers();
		Set<String> sts = ambspam.getSpammers();
		Set<String> lts = amblimpio.getSpammers();
		Set<String> spammers = new HashSet<String>() {
			private static final long serialVersionUID = 1L;
			{
				addAll(st);
				addAll(sts);
				addAll(lts);
			}
		};
		System.out.println("Usuarios marcados como spam: ");
		spammers.forEach(System.out::println);
	}
}