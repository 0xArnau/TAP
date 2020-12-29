package p2.mailstore;

import java.util.List;

import p1.messages.Message;
import p1.mailstore.*;


public abstract class MessageDecorator implements MailStore {
	private MailStore store;

	/**
	 * Constructor de MessageDecorator
	 * 
	 * @param store Mailstore
	 */
	public MessageDecorator(MailStore store) {
		this.store = store;
	}

	/**
	 * Acción que hace override a sendMail de MailStore. Encargada de enviar un
	 * correo de un usuario a otro.
	 * 
	 * @param u Persona a la que irá a parar el correo.
	 * @param m Variable de tipo Message (Consultar Message.java para más
	 *          información).
	 */
	@Override
	public void sendMail(String u, Message m) {
		this.store.sendMail(u, m);

	}

	/**
	 * Función que hace override a getMail de MailStore. Permite obtener los correos
	 * del buzón del usuario u.
	 * 
	 * @param u Usuario del que se quiere conseguir los correos.
	 * @return Devuelve una lista de los mensajes del usuario.
	 */
	@Override
	public List<Message> getMail(String u) throws Exception {
		return this.store.getMail(u);
	}
}
