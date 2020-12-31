package p4.system;

import p1.mailbox.*;
import p1.mailstore.*;
import p1.messages.*;
import p1.users.*;
import p4.dynamic.DynamicProxy;
import p4.mailstore.MailStoreAnnotation;

import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.util.Map;
import java.lang.annotation.Annotation;
import java.util.HashMap;

@MailStoreAnnotation (
	store = "p1.mailstore.InMemory",
	log = false
)
public class MailSystem {
	private Map<User, MailBox> administrative = new HashMap<User, MailBox>();
	private Map<String, User> users = new HashMap<String, User>();
	private String store = null;
	private boolean log = false;

	private void readAnnotation() {
		Class<MailSystem> obj = MailSystem.class;
		if (obj.isAnnotationPresent(MailStoreAnnotation.class)) {
			Annotation annotation = obj.getAnnotation(MailStoreAnnotation.class);
			MailStoreAnnotation msa = (MailStoreAnnotation) annotation;
			store = msa.store();
			log = msa.log();
		}
	}
	
	/**
	 * Función encargada de añadir un usuario a la Mailbox.
	 * 
	 * @param u     Usuario a añadir.
	 * @param store MailStore del usuario.
	 * @return Devuelve la MailBox recién creade del usuario u.
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public MailBox newUser(User u) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (getExist(u.getUserName())) {
			System.out.println("=> username: " + u.getUserName() + " already exists.");
			return null;
		} else {
			readAnnotation();
			Class aClass = Class.forName(store);
			MailStore ms = (MailStore) DynamicProxy.newInstance( (MailStore) aClass.newInstance(), log);
			MailBox box = new MailBox(u.getUserName(), ms);
			administrative.put(u, box);
			users.put(u.getUserName(), u);
			// System.out.println("=> user: " + u.getUserName() + " created.");
			return box;
		}
	}

	/**
	 * Función que permite inciciar sesión. También comprueba si este existe de
	 * verdad o no.
	 * 
	 * @param username Usuario a iniciar sesión.
	 * @return Devuelve true si el usuario existe y false en caso contrario.
	 */
	public boolean logIn(String username) {
		User user = getUser(username);
		if (user == null)
			return false;
		return true;
	}

	/**
	 * Función que permite obtener todos los mensajes del sistema.
	 * 
	 * @return Devuelve una lista con todos los mensajes.
	 */
	public List<Message> getAllMessages() throws Exception {
		Stream<Message> all = Stream.empty();
		List<List<Message>> list = administrative.values().stream().map(p -> p.listMail()).collect(Collectors.toList());
		for (List<Message> l : list) {
			if (l == null)
				continue;
			all = Stream.concat(all, l.stream());
		}
		return all.collect(Collectors.toList());
	}

	/**
	 * Acción encargada de actualizar todos los correos recibidos.
	 * 
	 * @throws Exception Excepción por si se pide actualizar el correo de alguien
	 *                   inexistente.
	 */
	public void updateMessages() throws Exception {
		for (Entry<User, MailBox> m : administrative.entrySet()) {
			if (m.getValue().updateMail() != null)
				m.getValue().updateMail();
		}
	}

	/**
	 * Función que permite obtener todos los usuarios registrados.
	 * 
	 * @return Devuelve una colección de los usuarios del sistema.
	 */
	public Set<User> getAllUsers() {
		return administrative.keySet();
	}

	/**
	 * Función que permite obtener todos los correos del sistema que contienen
	 * cierta palabra.
	 * 
	 * @param word Palabra a filtrar de los correos.
	 * @return Devuelve un Stream con todos los correos que han cumplido la
	 *         condición.
	 */
	public Stream<Message> filter(String word) throws Exception {
		return getAllMessages().stream().filter(str -> str.toString().contains(word));
	}

	/**
	 * Función que cuenta el número total de mensajes del sistema.
	 * 
	 * @return Devuelve el número total de mensajes en el sistema.
	 */
	public int countMessages() throws Exception {
		return getAllMessages().size();
	}

	/**
	 * Función que agrupa los correos del sistema por el sujeto del mismo.
	 * 
	 * @return Devuelve un Map con todos los correos que han cumplido la condición.
	 * @throws Exception Control de excpeciónes.
	 */
	public Map<String, List<Message>> groupBySubject() throws Exception {
		Map<String, List<Message>> all = getAllMessages().stream().collect(Collectors.groupingBy(Message::getSubject));
		return all;
	}

	/**
	 * Acción que permite saber la media de mensajes de los usuarios.
	 * 
	 * @throws Exception Excepción por si se pide la media de un usuario
	 *                   inexistente.
	 */
	public void averageMessagesPerUser() throws Exception {
		float size = getAllMessages().size();
		for (Entry<User, MailBox> m : administrative.entrySet()) {
			if (m.getValue().listMail() == null)
				continue;
			System.out.print("Username: " + m.getKey().getUserName() + " average messages received: ");
			System.out.println(m.getValue().listMail().size() / size);
		}
	}

	/**
	 * Función que muestra los correos del sistema con el sujeto word.
	 * 
	 * @param word Sujeto a filtrar de los correos.
	 * @return Devuelve un stream de los correos que han cumplido la condición.
	 * @throws Exception Control de excepción.
	 */
	public Stream<Message> filterSubject(String word) throws Exception {
		return getAllMessages().stream().filter(str -> str.getSubject().contains(word));
	}

	/**
	 * Función que permite filtrar correos enviados por una persona en específico y
	 * a la vez de un stream de correos en específico.
	 * 
	 * @param sender Persona de la que se quieren filtrar los correos.
	 * @param m      Stream de los correos
	 * @return Devuelve un stream con los correos que han cumplido la condición.
	 */
	public Stream<Message> filterBySender(String sender, Stream<Message> m) {
		return m.filter(p -> p.getFrom().equals(sender));
	}

	/**
	 * Función que permite filtrar correos enviados por una persona en específico.
	 * 
	 * @param sender Persona de la que se quieren filtrar los correos.
	 * @return Devuelve un stream de los crreos que han cumplido la condición.
	 * @throws Exception Excepción por si el usuario no existe.
	 */
	public Stream<Message> filterBySender(String sender) throws Exception {
		return getAllMessages().stream().filter(p -> p.getFrom().equals(sender));
	}

	/**
	 * Función que cuenta las palabras de los mensajes de un usuario en concrecto.
	 * 
	 * @param name Número del usuario a contar.
	 * @return Devuelve un int con el número total de palabras.
	 * @throws Exception Excepción por si el usuario no existe.
	 */
	public int countWordsOfMessagesFromUser(String name) throws Exception {
		return getAllMessages().stream().filter(p -> getUser(p.getFrom()).getName().equals(name))
				.map(x -> x.getBody().split(" ").length).collect(Collectors.summingInt(Integer::intValue));
	}

	/**
	 * Función que permite obtener los correos de usuarios que sean de un año
	 * anterior o más a year.
	 * 
	 * @param year Año con el que se va a comparar y obtener de años más antiguos.
	 * @return Devuelve una lista con los correos que han cumplido la condición.
	 * @throws Exception Control de excepción.
	 */
	public List<Message> usersBornBeforeXYear(int year) throws Exception {
		return getAllMessages().stream().filter(p -> getUser(p.getTo()).getYear() < year).collect(Collectors.toList());
	}

	/**
	 * Función que permite obtener los correos de usuarios que sean de un año
	 * posterior o más a year.
	 * 
	 * @param year Año con el que se va a comparar y obtener de años posteriores.
	 * @return Devuelve una lista con los correos que han cumplido la condición.
	 * @throws Exception Control de excepción.
	 */
	public List<Message> usersBornAfterXYear(int year) throws Exception {
		return getAllMessages().stream().filter(p -> getUser(p.getFrom()).getYear() > year)
				.collect(Collectors.toList());
	}

	/**
	 * Función que devuelve los correos que contengan la palabra word y a la vez
	 * contengan menos de n palabras.
	 * 
	 * @param m    Lista de mensajes.
	 * @param word Palabra a filtrar.
	 * @param n    Número de palabras a filtrar.
	 * @return Devuelve un stream con los mensajes que han cumplido la condición.
	 * @throws Exception Control de excepción.
	 */
	public Stream<Message> containsXWordAndLessthanNWords(List<Message> m, String word, int n) throws Exception {
		return m.stream().filter(p -> p.toString().contains(word) && p.getBody().split(" ").length < n);
	}

	/**
	 * Función que devuelve un stream de los mensajes con cierta palabra word.
	 * 
	 * @param m    Lista de mensajes a comprobar.
	 * @param word Palabra a buscar.
	 * @return Devuelve los mensajes que han cumplido la condición.
	 * @throws Exception Control de excepción.
	 */
	public Stream<Message> containsXWord(List<Message> m, String word) throws Exception {
		return m.stream().filter(p -> p.toString().contains(word));
	}

	/**
	 * Función que devuelve un stream de mensajes con menos de n palabras en él.
	 * 
	 * @param m Lista de mensajes a comprobar.
	 * @param n Número de palabras a filtrar.
	 * @return Devuelve los mensajes que han cumplido la condición.
	 * @throws Exception Control de excepción.
	 */
	public Stream<Message> lessthanNWords(List<Message> m, int n) throws Exception {
		return m.stream().filter(p -> p.getBody().split(" ").length < n);
	}

	/**
	 * Función que devuelve los correos que contienen una sola palabra en el sujeto.
	 * 
	 * @param m Lista de mensajes a comprobar.
	 * @return Devuelve los mensajes que han cumplido la condición.
	 */
	public Stream<Message> subjectSingleWord(List<Message> m) {
		return m.stream().filter(p -> p.getSubject().split(" ").length == 1);
	}

	/**
	 * Función que permite obtener el usuario con nombre username.
	 * 
	 * @param username Nombre a obtener el usuario.
	 * @return Devuelve el User con nombre username.
	 */
	public User getUser(String username) {
		return users.get(username);
	}

	/**
	 * Función que permite obtener la MailBox de un usuario con nombre username.
	 * 
	 * @param username Nombre del usuario del que obtener su MailBox.
	 * @return Devuelve la MailBox del usuario.
	 */
	public MailBox getMailBoxOfUser(String username) {
		try {
			return administrative.get(getUser(username));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Función alternativa que permite obtener la MailBox de un usuario.
	 * 
	 * @param username Usuario del que obtener su MailBox.
	 * @return Devuelve la MailBox del usuario.
	 */
	public MailBox getMailBoxOfUser(User username) {
		return administrative.get(username);
	}

	/**
	 * Acción que printea el usuario.
	 */
	public void printUsers() {
		administrative.forEach((key, value) -> System.out.println("User: " + key));
	}

	/**
	 * Función que comprueba que un usuario con nombre username exista.
	 * 
	 * @param username Nombre del usuario.
	 * @return Devuelve cierto o falso dependiendo de si cumple o no la condición.
	 */
	public boolean getExist(String username) {
		return users.get(username) != null ? true : false;
	}

	/**
	 * Permite resetear el correo. Muy útil para hacer tests del funcionamiento.
	 */
	public void reset() {
		administrative = new HashMap<User, MailBox>();
		users = new HashMap<String, User>();
	}

}
