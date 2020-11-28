import system.*;
import users.*;
import messages.*;
import mailbox.*;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 

public class Cli {
	private static User user;
	private static MailBox box;
	private static BufferedReader reader =
		new BufferedReader(new InputStreamReader(System.in));
	private static int input;

	public static void main(String[] args) throws Exception {
		
		do {
			clearWindow(2);
			System.out.println("0 -> EXIT");
			System.out.println("1 -> New user\t\t 2 -> LogIn\t\t 3 -> List users");//login no password
			System.out.print(">> ");
			input = Integer.parseInt(reader.readLine());

			switch (input) {
				case 0: 
					return;
				//break;
				case 1:
					clearWindow(100);
					askData4NewUser();
				break;
				case 2:
					clearWindow(100);
					asUser(logIn());
					input = 1;
				break;
				case 3:
					clearWindow(100);
					MailSystem.printUsers();
					input = 1;
				break;
				default: System.out.println("ERROR");
			}
		} while(input != 0);
	}

	private static void askData4NewUser() throws IOException {
		System.out.println("Name:");
		System.out.print(">> ");
		String name = reader.readLine();
		System.out.println("Username:");
		System.out.print(">> ");
		String username= reader.readLine();
		System.out.println("born:");
		System.out.print(">> ");
		int	born = Integer.parseInt(reader.readLine());
		
		user = new User(name,username,born);
		System.out.print("Mail in mem(0)/on file(1): >> ");
		box = MailSystem.newUser(user,Boolean.parseBoolean(reader.readLine()));
	}

	private static boolean logIn() throws IOException {
		String username;
		boolean valid;
		do {
			System.out.print("Username:\n>> ");
			username = reader.readLine();
		} while (!(valid = MailSystem.logIn(username)) && !username.equalsIgnoreCase("exit"));
		user = MailSystem.getUser(username);
		return valid;
	}

	private static void asUser(boolean valid) throws IOException {
		clearWindow(100);
		if (!valid)
			return;
		System.out.println("["+user+"]");

	//	System.out.print("Mail in mem(0)/on file(1): >> ");


		clearWindow(3);
		do {
			System.out.println("0 -> EXIT\t\t 1 -> Send mail");
			System.out.println("2 -> update\t\t 3 -> list");
			System.out.println("4 -> sort\t\t 5 -> filter");
			System.out.print(">> ");
			try {
				input = Integer.parseInt(reader.readLine());
			} catch (Exception e) {
				input = -1;
			}
		

			switch (input) {
				case 0: 
					clearWindow(100);
					return;
				//break;
				case 1:
					sendMail();
				break;
				case 2:
					update();
				break;
				case 3:
					clearWindow(10);
					list();
					System.out.print(">>");
					reader.readLine();
				break;
				case 4:
					sort();
				break;
				case 5:
					filter();
				break;
				default: System.out.println("ERROR: invalid input");
			}
			clearWindow(1);
		} while(input != 0);
	}
	
	private static void sendMail() throws IOException {
		System.out.println("To:");
		System.out.print(">> ");
		String to = reader.readLine();
		System.out.println("Subject:");
		System.out.print(">> ");
		String subject = reader.readLine();
		System.out.println("Body:");
		System.out.print(">> ");
		String body = reader.readLine();

		if (!MailSystem.getExist(to)) {
			System.out.println("ERROR: username");
			return;
		}
		box.sendMail(MailSystem.getUser(to), subject, body);
	}



	private static void update() {
		try {
			box.updateMail();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
	}
	private static void list() {
		try {
			for (Message m : box) {
				System.out.println(m);
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
	}
	private static void sort() {}
	private static void filter() {}

	private static void clearWindow(int x) {
		for (int i = 0; i < x; i++)
			System.out.println();
	}
}
