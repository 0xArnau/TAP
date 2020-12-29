package p2.tests;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import p1.mailbox.*;
import p1.mailstore.*;
import p2.mailbox.*;
import p2.mailstore.*;
import p1.users.*;
import p1.messages.*;


@SuppressWarnings("unused")
public class TestP2 {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException {
		System.out.println("p2.src");
		System.out.println("Observer");

		User star = new User("star", "arnau", 2000);
		User spam = new User("spam", "spam", 2000);
		MailStore ms = new InMemory();
		MailBox mb = new MailBox("star", ms);
		MailBox mbsp = new MailBox("spam", ms);

		mb.sendMail("star", "subject", "body");
		System.out.println("1");
		mb.sendMail("star", "subject", "123456789010111213141516171819202122232425262728290");
		System.out.println("2");
		mb.sendMail("star", "spam", "body");
		System.out.println("3");
		mbsp.sendMail("star", "spam", "spam");
		System.out.println("4");
		mbsp.sendMail("star", "spam", "wtf");
		System.out.println("5");
		mb.sendMail("spam", "k", "w");

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

		System.out.println("AMB");
		AutomaticMailBox ambstar = new AutomaticMailBox("star", ms);
		AutomaticMailBox ambspam = new AutomaticMailBox("spam", ms);
		System.out.println("TLF");
		TooLongFilter t = new TooLongFilter();
		System.out.println("SUF");
		SpamUserFilter s = new SpamUserFilter();
		ambstar.attach(t);
		ambstar.attach(s);
		ambspam.attach(t);
		ambspam.attach(s);

		System.out.println("STAR");
		try {
			ambstar.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("SPAM");
		try {
			ambspam.updateMail().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("spammers");
		Set<String> st = ambstar.getSpammers();
		Set<String> sts = ambspam.getSpammers();
		Set<String> spammers = new HashSet<String>() {
			private static final long serialVersionUID = -1632603294655041069L;

			{
				addAll(st);
				addAll(sts);
			}
		};

		spammers.forEach(System.out::println);
		
		System.out.println("Cypher");
		String key = "IWantToPassTAp12"; // 128 bit key
		java.security.Key aesKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");

		System.out.println(key);
		System.out.println(aesKey);
		System.out.println(cipher);
		System.out.println("Encrypt");
		byte[] encrypted = new byte[0];
		try {
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			encrypted = cipher.doFinal(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String idk = Base64.getEncoder().encodeToString(encrypted);
		System.out.println(idk);
		System.out.println("Decrypt");

		byte[] encrypted1 = Base64.getDecoder().decode(idk.getBytes());
		String decrypted = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decrypted = new String(cipher.doFinal(encrypted1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(decrypted);
		
		System.out.println("Decorator");

		MailStore none = new OnFile();
		EncodeDecorator deco = new EncodeDecorator(none);
		deco.setCipher();
		deco.sendMail("star", new Message("star", "star", "subject", "body"));
		try {
			deco.getMail("star").forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
