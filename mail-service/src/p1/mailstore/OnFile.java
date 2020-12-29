package p1.mailstore;

import p1.messages.*;

import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class OnFile implements MailStore {

	private static String fileName = "mail_store.txt";

	/**
	 * Acción encargada de comprobar y crear un nuevo archivo si este no existe.
	 * 
	 * @throws Exception Excepción por si hay un problema a la hora de crear el
	 *                   archivo.
	 */
	private static void createFile() throws Exception {
		File log = new File(fileName);
		if (log.createNewFile()) {
			System.out.println("[File created]");
		} else {
			System.out.println("[File already exists]");
		}
	}

	/**
	 * Acción encargada de escribir en el archivo un mensaje.
	 * 
	 * @param message Mensaje a escribir en el archivo.
	 * @throws Exception Exepción por si hay un problema a la hora de escribir en el
	 *                   archivo.
	 */
	private static void writeFile(String message) throws Exception {
		createFile();

		FileWriter log = new FileWriter(fileName, true);
		log.write(message);
		log.close();
		System.out.println("[added successfully]");
	}

	/**
	 * Acción encargada de enviar un correo de un usuario a otro.
	 * 
	 * @param to Persona a la que irá a parar el correo.
	 * @param m  Variable de tipo Message (Consultar Message.java para más
	 *           información).
	 */
	public void sendMail(String u, Message m) {
		try {
			if (m.toString().matches(".+;" + u + ";.+"))
				writeFile(/* u.getUserName() + ";" + */ m.toString() + "\n");
			else
				System.out.println("ERROR ==> Message(to) != User");
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
	}

	/**
	 * Función que permite obtener los correos del buzón del usuario u.
	 * 
	 * @param u Usuario del que se quiere conseguir los correos.
	 * @return Devuelve una lista de los mensajes del usuario.
	 */
	public List<Message> getMail(String u) throws Exception {
		String line;
		List<Message> list = new LinkedList<Message>();
		try {
			File log = new File(fileName);
			Scanner input = new Scanner(log);
			while (input.hasNextLine()) {
				line = input.nextLine();
				String[] part = line.split(regex);
				if (part[1].equals(u)) {

					String from = part[0];
					String to = part[1];
					if (from == null || to == null) {

					} else {
						list.add(new Message(from, to, part[2], part[3], part[4]));
					}
				}
			}
			input.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			return null;
		}
		return new LinkedList<Message>(list);
	}
}
