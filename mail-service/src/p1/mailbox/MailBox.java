package p1.mailbox;

import p1.messages.*;
import p1.mailstore.*;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MailBox implements Iterable<Message> {
	private String user;
	private List<Message> messages;
	private MailStore store;

	/**
	 * Constructor de MailBox.
	 * 
	 * @param u     Nom del usuari.
	 * @param store MailStore del usuari.
	 */
	public MailBox(String u, MailStore store) {
		user = u;
		this.store = store;
	}

	/**
	 * Función encargada de actualizar el correo recibido.
	 * 
	 * @return Devuelve on stream de todos los correos recibidos.
	 * @throws Exception Caso edge donde se pida actualizar el correo de alguien
	 *                   inexistente.
	 */
	public Stream<Message> updateMail() throws Exception {
		messages = store.getMail(user);
		if (messages == null)
			return null;
		return messages.stream();
	}

	/**
	 * Función que devuelve una lista con todos los correos ya sean globales o de un
	 * usuario en concreto.
	 * 
	 * @return Devuelve los mensajes en forma de lista.
	 */
	public List<Message> listMail() {
		return messages;
	}

	/**
	 * Acción encargada de enviar un correo de un usuario a otro.
	 * 
	 * @param to      Persona a la que irá a parar el correo.
	 * @param subject Sujeto del correo.
	 * @param body    Contenido en sí del correo.
	 */
	public void sendMail(String to, String subject, String body) {
		store.sendMail(to, new Message(user, to, subject, body));
	}

	/**
	 * Acción alternativa encargada de enviar un correo de un usuario a otro.
	 * 
	 * @param to Persona a la que irá a parar el correo.
	 * @param m  Variable de tipo Message (Consultar Message.java para más
	 *           información).
	 */
	public void sendMail(String to, Message m) {
		store.sendMail(to, m);
	}

	/**
	 * Función encargada de filtrar los correos por una palabra concreta.
	 * 
	 * @param p Palabra a filtrar.
	 * @return Devuelve un stream de los mensajes que hayan cumplido el filtro.
	 */
	public Stream<Message> filterMail(String p) {
		return messages.stream().filter(str -> str.toString().contains(p));
	}

	/**
	 * Función encargada de ordenar el correo recibido.
	 * 
	 * @return Devuelve un stream de los mensajes ordenados.
	 */
	public Stream<Message> sortMail() {
		return messages.stream().sorted().collect(Collectors.toList()).stream(); // sorted(Comparator.reverseOrder())
																					// sorted()
	}

	/**
	 * Función encargada de ordenar el correo, pero por la persona que ha enviado el
	 * correo.
	 * 
	 * @return Devuelve un stream de los mensajes ordenados por sender.
	 */
	public List<Message> sortMailBySender() {
		return messages.stream().sorted(Comparator.comparing(Message::getFrom)).collect(Collectors.toList());
	}

	/**
	 * Función encargada de devolver un iterador de los Messages.
	 */
	@Override
	public Iterator<Message> iterator() {
		if (messages == null)
			return null;
		return messages.iterator();
	}

	/**
	 * Función que permite buscar correos que contengan un sujeto en específico.
	 * 
	 * @param word Sujeto por el que buscar en los correos.
	 * @return Devuelve un stream de los correos que cumplen la condición.
	 * @throws Exception Excepción por si se pide buscar de un usuario inexistente
	 *                   entre otros.
	 */
	public Stream<Message> filterSubject(String word) throws Exception {
		return messages.stream().filter(str -> str.getSubject().contains(word));
	}

}