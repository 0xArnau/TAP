package mailstore;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import messages.Message;

public class EncodeDecorator extends MessageDecorator {
	private Cipher cipher = null;
	private java.security.Key aesKey = null;

	public EncodeDecorator(MailStore store) {
		super(store);
	}

	@Override
	public List<Message> getMail(String u) throws Exception {
		return super.getMail(u).stream()
			.map(p -> new Message(p.getFrom(), p.getTo(), p.getSubject(), decrypt(p.getBody())))
			.collect(Collectors.toList());
	}

	@Override
	public void sendMail(String u, Message m) {
		Message message = new Message(m.getFrom(), m.getTo(), m.getSubject(), encrypt(m.getBody()));
		super.sendMail(u, message);
	}

	public void setCipher() {
		String key = "IWantToPassTAP12"; // 128 bit key
		aesKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public String encrypt(String body) {
		if (cipher == null || aesKey == null)
			return null;
			byte[] encrypted = new byte[0];
			try {cipher.init(Cipher.ENCRYPT_MODE, aesKey);
				encrypted = cipher.doFinal(body.getBytes());
			} catch (Exception e) {e.printStackTrace();
			}
			return  Base64.getEncoder().encodeToString(encrypted);
	}

	public String decrypt(String body) {
		if (cipher == null || aesKey == null)
			return null;
		byte[] encrypted1 = Base64.getDecoder().decode(body.getBytes());
		String decrypted = null;
		try {cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decrypted = new String(cipher.doFinal(encrypted1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decrypted;
	}
}
