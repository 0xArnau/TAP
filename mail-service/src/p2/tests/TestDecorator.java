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
		String key = "IWantToPassTAp1.src2"; // 128 bit key
		java.security.Key aesKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");

		System.out.println("Key: " + key);
		System.out.println(aesKey);
		System.out.println(cipher);

		System.out.println("Encryption in process...");
		byte[] encrypted = new byte[0];
		try {
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			encrypted = cipher.doFinal(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String encryption = Base64.getEncoder().encodeToString(encrypted);
		System.out.println(encryption);
		
		System.out.println("Decryption in process...");
		byte[] encrypted1 = Base64.getDecoder().decode(encryption.getBytes());
		String decrypted = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decrypted = new String(cipher.doFinal(encrypted1));
		} catch (Exception e) {
			e.printStackTrace();
		}

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
