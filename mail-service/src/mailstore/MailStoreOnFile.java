package mailstore;

import users.*;
import messages.*;

import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class MailStoreOnFile extends MailStore{

	private String regex = ";";
	private String fileName = "mail_store.txt";

	public MailStoreOnFile(String fileName){
		this.fileName = fileName;
	}

	// Mètode que crea un fitxer, si aquest no existeix.
	private void createFile() throws Exception {
		File log = new File(fileName);
		if (log.createNewFile()) {
			System.out.println("[File created]");
		} else {
			System.out.println("[File already exists]");
		}
	}

	// Mètode que afegeix el @message al final del fitxer.
	private void writeFile(String message) throws Exception {
		FileWriter log = new FileWriter(fileName, true);
		log.write(message);
		log.close();
		System.out.println("[added successfully]");
	}

	private void readFile(User u) throws Exception {
		File log = new File(fileName);
		Scanner input = new Scanner(log);
		String line;

		while (input.hasNextLine()) {
			line = input.nextLine();
			if (line.matches("^From: " + u.getUserName() + ";.+"))
				System.out.println(line);
		}

		input.close();
	}
}
