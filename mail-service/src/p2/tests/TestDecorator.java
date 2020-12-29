package p2.tests;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import p2.mailstore.EncodeDecorator;
import p1.mailstore.MailStore;
import p1.mailstore.OnFile;
import p1.messages.Message;


@SuppressWarnings("unused")
public class TestDecorator {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException {

		System.out.println("Decorator");
		MailStore none = new OnFile();
		EncodeDecorator deco = new EncodeDecorator(none);
		deco.setCipher();
		deco.sendMail("star", new Message("star", "star", "subject", "body"));
		System.out.println("Mail decrypted: ");
		try {
			deco.getMail("star").forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
