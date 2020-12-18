import system.*;
import messages.*;
import mailstore.*;
import mailbox.*;
import users.*;


import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 

public class Cli {
	private static User user;
	private static MailBox box;
	private static BufferedReader reader =
		new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		nonUsers();
	}
	private static void nonUsers() throws Exception {
		String [] info;
		info = waiting4commandNonUsers();
		try {
			switch (info[0]) {
				case "createuser":
					if (info.length < 5) {
						System.out.println("ERROR\nTry again:" + info.length);
						nonUsers();
					}
					if (info[4].equals("file"))
						box = MailSystem.newUser(new User(info[1], info[2], Integer.parseInt(info[3])), new OnFile());
					else
						box = MailSystem.newUser(new User(info[1], info[2], Integer.parseInt(info[3])), new InMemory());
					nonUsers();
				break;
				case "filter":
				break;
				case "logas":
					if (MailSystem.logIn(info[1])) {
						user = MailSystem.getUser(info[1]);
						users();
					}
					else {
						System.out.println("ERROR: username\nTry again:");
						nonUsers();
					}
				break;
				case "exit":
				break;
				default:
				System.out.println("ERROR\nTry again:");
				nonUsers();
				break;
			}
		} catch (Exception e) {
			System.out.println("ERROR\nTry again:");
			nonUsers();
		}
	}

	private static String [] waiting4commandNonUsers() {
		
		try {
			System.out.println("Commands availables 4 non users:");
			System.out.println("\tcreateuser <username><name><year of birth><file/memory>");
			System.out.println("\tfilter <contains<word>> <lessthan<n words>>");
			System.out.println("\tlogas <username>");
			System.out.println("\texit");
			System.out.print(">> ");
			String [] info = reader.readLine().split(" ");
			return info;
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			System.out.println("Try again:");
			waiting4commandNonUsers();
		}
		return null;
	}

	private static void users() throws Exception {
		String [] info;
		info = waiting4commandUsers();
		try {
			switch (info[0]) {
				case "send":
					System.out.print("Subject:\n>> ");
					String subject = reader.readLine();
					System.out.print("Body:\n>> ");
					String body = reader.readLine();
					box.sendMail(info[1], new Message(user.getUserName(), info[1], subject, body));
					users();
				break;
				case "update":
					box.updateMail().forEach(System.out::println);
					users();
				break;
				case "list":
					box.listMail().forEach(System.out::println);
					users();
				break;
				case "sort":
					box.sortMail().forEach(System.out::println);
					users();
				break;
				case "filter":
					box.filterMail(info[2]).forEach(System.out::println);
					users();
				break;
				case "exit":
					nonUsers();
				break;
				default:
					System.out.println("ERROR\nTry again:");
					users();
				break;
			}
		}
		catch (Exception e) {
			System.out.println("ERROR\nTry again:");
			users();
		}
	}
	private static String [] waiting4commandUsers() {
		try {
			System.out.println("\n\n" + user);
			System.out.println("Commands availables 4 users:");
			System.out.println("\tsend <to>");
			System.out.println("\tupdate");
			System.out.println("\tlist");
			System.out.println("\tsort  <>");
			System.out.println("\tfilter <contains<word>> <lessthan<n words>>");
			System.out.println("\texit");
			System.out.print(">> ");
			String [] info = reader.readLine().split(" ");
			return info;
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			System.out.println("Try again:");
			waiting4commandUsers();
		}
		return null;
	}
}
