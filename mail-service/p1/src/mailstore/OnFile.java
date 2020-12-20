package mailstore;

import messages.*;

import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class OnFile implements MailStore {

	private static String fileName = "mail_store.txt";

	// Mètode que crea un fitxer, si aquest no existeix.
	private static void createFile() throws Exception {
		File log = new File(fileName);
		if (log.createNewFile()) {
			System.out.println("[File created]");
		} else {
			System.out.println("[File already exists]");
		}
	}

	// Mètode que afegeix el @message al final del fitxer.
	private static void writeFile(String message) throws Exception {
		createFile();

		FileWriter log = new FileWriter(fileName, true);
		log.write(message);
		log.close();
		System.out.println("[added successfully]");
	}

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

	public List<Message> getMail(String u) {
		String line;
		List<Message> list = new LinkedList<Message>();
		try {
			File log = new File(fileName);
			Scanner input = new Scanner(log);
			while (input.hasNextLine()) {
				line = input.nextLine();
				if (line.matches(".+;" + u + ";.+")) {
					String[] part = line.split(regex);
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
