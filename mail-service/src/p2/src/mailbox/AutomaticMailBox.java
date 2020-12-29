package p2.src.mailbox;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import p1.src.mailstore.*;
import p1.src.messages.Message;
import p1.src.mailbox.*;

public class AutomaticMailBox extends MailBox implements Subject {
	private List<Observer> list = new LinkedList<Observer>();

	/**
	 * Constructor de AutomaticMailBox.
	 * 
	 * @param u     Nombre de usuario.
	 * @param store MailStore del usuario.
	 */
	public AutomaticMailBox(String u, MailStore store) {
		super(u, store);
	}

	// <Observer>
	/**
	 * Acción que permite añadir un Observer.
	 * 
	 * @param o Observer que se va a añadir.
	 */
	@Override
	public void attach(Observer o) {
		list.add(o);
	}

	// <Observer>
	/**
	 * Acción que permite borrar un Observer de la lista.
	 * 
	 * @param o Observer que se va a borrar de la lista.
	 */
	@Override
	public void detach(Observer o) {
		list.remove(o);
	}

	/**
	 * Acción que notificará a todos los Observers de la actualización.
	 */
	@Override
	public void notifyObservers() {
		for (Observer o : list) {
			o.update(super.listMail());
		}
	}

	// </Observer>
	/**
	 * Función encargada de actualizar el correo recibido.
	 * 
	 * @return Devuelve on stream de todos los correos recibidos.
	 * @throws Exception Caso edge donde se pida actualizar el correo de alguien
	 *                   inexistente.
	 */
	@Override
	public Stream<Message> updateMail() throws Exception {
		super.updateMail();
		notifyObservers();
		return super.listMail().stream();
	}

	/**
	 * Función que permite obtener todos los usuarios que son considerados spammers.
	 * 
	 * @return Devuelve una colección con el nombre de los usuarios considerados
	 *         spammers.
	 */
	public Set<String> getSpammers() {
		Set<String> spammers = new HashSet<String>();
		for (Observer o : list) {
			try {
				SpamUserFilter suf = ((SpamUserFilter) o);
				for (String from : suf.getMessages().stream().map(p -> p.getFrom()).collect(Collectors.toSet())) {
					spammers.add(from);
				}
			} catch (Exception e) {
			}
			try {
				TooLongFilter stf = ((TooLongFilter) o);
				for (String from : stf.getMessages().stream().map(p -> p.getFrom()).collect(Collectors.toSet())) {
					spammers.add(from);
				}
			} catch (Exception e) {
			}
		}
		return spammers;
	}
}
