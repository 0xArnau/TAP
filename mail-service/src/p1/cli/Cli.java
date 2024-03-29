package p1.cli;

import p1.system.*;
import p1.messages.*;
import p1.mailbox.*;
import p1.mailstore.InMemory;
import p1.mailstore.MailStore;
import p1.mailstore.OnFile;
import p1.users.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Cli {
	private static MailSystem system = new MailSystem();
	private static User user;
	private static MailBox box;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		nonUsers();
	}

	/**
	 * Acción que mostrará a los visitantes algunas de las opciones que pueden
	 * realizar.
	 * 
	 * @throws Exception Control de excepciones.
	 */
	private static void nonUsers() throws Exception {
		boolean exit = false;
		do {
			String[] info;
			info = waiting4commandNonUsers();
			try {
				switch (info[0]) {
					case "createuser":
						if (info.length < 5)
							System.out.println("ERROR\nTry again:" + info.length);
						else {
							MailStore store;
							if (info[4].equals("file"))
								store = new OnFile();
							else if (info[4].equals("memory"))
								store = new InMemory();
							else {
								System.out.println("ERROR\nTry again:" + info.length);
								break;
							}
							system.newUser(new User(info[1], info[2], Integer.parseInt(info[3])), store);
						}
						break;
					case "filter":
						if (info.length == 5) {
							try {
								system.containsXWordAndLessthanNWords(system.getAllMessages(), info[2],
										Integer.parseInt(info[4])).forEach(System.out::println);
							} catch (Exception e) {
								System.out.println("ERROR\nTry again:");
							}
						} else if (info.length == 3) {
							switch (info[1]) {
								case "contains":
									try {
										system.containsXWord(system.getAllMessages(), info[2])
												.forEach(System.out::println);
									} catch (Exception e) {
										System.out.println("ERROR\nTry again:");
									}
									break;
								case "lessthan":
									try {
										system.lessthanNWords(system.getAllMessages(), Integer.parseInt(info[2]))
												.forEach(System.out::println);
									} catch (Exception e) {
										System.out.println("ERROR\nTry again:");
									}
									break;
								default:
									System.out.println("ERROR\nTry again:");
									break;
							}
						} else
							System.out.println("ERROR\nTry again:");
						break;
					case "logas":
						if (system.logIn(info[1])) {
							user = system.getUser(info[1]);
							box = system.getMailBoxOfUser(user);
							users();
						} else {
							System.out.println("ERROR: username\nTry again:");
						}
						break;
					case "exit":
						exit = true;
						break;
					default:
						System.out.println("ERROR\nTry again:");
						break;
				}
			} catch (Exception e) {
				System.out.println("ERROR\nTry again:");
			}
		} while (!exit);
	}

	/**
	 * Función que muestra los 4 comandos que puede realizar un visitante.
	 * 
	 * @return Devuelve la opción escogida por el visitante o null si se ha
	 *         equivocado.
	 */
	private static String[] waiting4commandNonUsers() {
		try {
			System.out.println("Commands availables 4 non users:");
			System.out.println("\tcreateuser <username> <name> <year of birth> <file/memory>");
			System.out.println("\tfilter <contains <word>> <lessthan <n words>>");
			System.out.println("\tlogas <username>");
			System.out.println("\texit");
			System.out.print(">> ");
			String[] info = reader.readLine().split(" ");
			return info;
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			System.out.println("Try again:");
			waiting4commandNonUsers();
		}
		return null;
	}

	/**
	 * Versión alternativa de las opciones pero esta vez para usuarios logeados.
	 * 
	 * @throws Exception Control de excepción.
	 */
	private static void users() throws Exception {
		boolean exit = false;
		do {
			String[] info;
			info = waiting4commandUsers();
			try {
				switch (info[0]) {
					case "send":
						System.out.print("Subject:\n>> ");
						String subject = reader.readLine();
						System.out.print("Body:\n>> ");
						String body = reader.readLine();
						box.sendMail(info[1], new Message(user.getUserName(), info[1], subject, body));
						break;
					case "update":
						box.updateMail().forEach(System.out::println);
						break;
					case "list":
						box.listMail().forEach(System.out::println);
						break;
					case "sort":
						if (info.length >= 2) {
							if (info[1].equals("sender"))
								box.sortMailBySender().forEach(System.out::println);
							else
								box.sortMail().forEach(System.out::println);
						} else {
							box.sortMail().forEach(System.out::println);
						}
						break;
					case "filter":
						if (info.length == 5) {
							try {
								system.containsXWordAndLessthanNWords(system.getAllMessages(), info[2],
										Integer.parseInt(info[4])).forEach(System.out::println);
							} catch (Exception e) {
								System.out.println("ERROR\nTry again:");
							}
						} else if (info.length == 3) {
							switch (info[1]) {
								case "contains":
									try {
										system.containsXWord(system.getAllMessages(), info[2])
												.forEach(System.out::println);
									} catch (Exception e) {
										System.out.println("ERROR\nTry again:");
									}
									break;
								case "lessthan":
									try {
										system.lessthanNWords(system.getAllMessages(), Integer.parseInt(info[2]))
												.forEach(System.out::println);
									} catch (Exception e) {
										System.out.println("ERROR\nTry again:");
									}
									break;
								default:
									System.out.println("ERROR\nTry again:");
									break;
							}
						}
						break;
					case "exit":
						exit = true;
						break;
					default:
						System.out.println("ERROR\nTry again:");
						break;
				}
			} catch (Exception e) {
				System.out.println("ERROR\nTry again:");
			}
		} while (!exit);
	}

	/**
	 * Función que muestra los 4 comandos que puede realizar un usuario logeado.
	 * 
	 * @return Devuelve la opción escogida por el visitante o null si se ha
	 *         equivocado.
	 */
	private static String[] waiting4commandUsers() {
		try {
			System.out.println("\n\n" + user);
			System.out.println("Commands availables 4 users:");
			System.out.println("\tsend <to>");
			System.out.println("\tupdate");
			System.out.println("\tlist");
			System.out.println("\tsort  <time(default)/sender>");
			System.out.println("\tfilter <contains<word>> <lessthan<n words>>");
			System.out.println("\texit");
			System.out.print(">> ");
			String[] info = reader.readLine().split(" ");
			return info;
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			System.out.println("Try again:");
			waiting4commandUsers();
		}
		return null;
	}
}
