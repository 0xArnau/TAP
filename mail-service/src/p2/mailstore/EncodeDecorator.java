package p2.mailstore;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import p1.messages.Message;
import p1.mailstore.*;


public class EncodeDecorator extends MessageDecorator {
	private Cipher cipher = null;
	private java.security.Key aesKey = null;

	/**
	 * Constructor de EncodeDecorator
	 * 
	 * @param store MailStore de la que se heredan sus atributos.
	 */
	public EncodeDecorator(MailStore store) {
		super(store);
		setCipher();
	}

	/**
	 * Función que hace override a getMail de MessageDecorator. Permite obtener los
	 * correos del buzón del usuario u.
	 * 
	 * @param u Usuario del que se quiere conseguir los correos.
	 * @return Devuelve una lista de los mensajes del usuario.
	 */
	@Override
	public List<Message> getMail(String u) throws Exception {
		return super.getMail(u).stream()
				.map(p -> new Message(p.getFrom(), p.getTo(), p.getSubject(), decrypt(p.getBody())))
				.collect(Collectors.toList());
	}

	/**
	 * Acción que hace override a sendMail de MessageDecorator. Encargada de enviar
	 * un correo de un usuario a otro.
	 * 
	 * @param u Persona a la que irá a parar el correo.
	 * @param m Variable de tipo Message (Consultar Message.java para más
	 *          información).
	 */
	@Override
	public void sendMail(String u, Message m) {
		Message message = new Message(m.getFrom(), m.getTo(), m.getSubject(), encrypt(m.getBody()));
		super.sendMail(u, message);
	}

	/**
	 * Acción encargada de iniciar el Cipher con llave IWantToPassTAp12 decidida por
	 * enunciado.
	 */
	public void setCipher() {
		String key = "IWantToPassTAp12"; // 128 bit key
		aesKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Función encargada de encriptar el mensaje de un usuario.
	 * 
	 * @param body Contenido del mensaje a encriptar.
	 * @return Devuelve el mensaje encriptado de tipo String.
	 */
	public String encrypt(String body) {
		if (cipher == null || aesKey == null)
			return null;
		byte[] encrypted = new byte[0];
		try {
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			encrypted = cipher.doFinal(body.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Base64.getEncoder().encodeToString(encrypted);
	}

	/**
	 * Función encargada de deshacer la encriptación.
	 * 
	 * @param body Contenido del mensaje a desencriptar.
	 * @return Devuelve el mensaje desencriptado de tipo String.
	 */
	public String decrypt(String body) {
		if (cipher == null || aesKey == null)
			return null;
		byte[] encrypted1 = Base64.getDecoder().decode(body.getBytes());
		String decrypted = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decrypted = new String(cipher.doFinal(encrypted1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decrypted;
	}
}
