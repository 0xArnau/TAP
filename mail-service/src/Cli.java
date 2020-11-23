import system.*;
import users.*;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 

public class Cli {
	private static User user;
	private static String name;
	private static String username;
	private static int born;
	private static BufferedReader reader =
		new BufferedReader(new InputStreamReader(System.in));
	private static int input;

	public static void main(String[] args) throws Exception {
		
		do {
			System.out.println("0 -> EXIT");
			System.out.println("1 -> New user\t\t 2 -> LogIn");//login no password
			System.out.print(">> ");
			input = Integer.parseInt(reader.readLine());

			switch (input) {
				case 0: 
					return;
				//break;
				case 1:
					clearWindow();
					askData4NewUser();
				break;
				case 2:
					clearWindow();
					asUser(logIn());
					input = 1;
				break;
				default: System.out.println("ERROR");
			}
			clearWindow();
		} while(input != 0);
	}

	private static void askData4NewUser() throws IOException {
		System.out.println("Name:");
		System.out.print(">> ");
		name = reader.readLine();
		System.out.println("Username:");
		System.out.print(">> ");
		username= reader.readLine();
		System.out.println("born:");
		System.out.print(">> ");
		born = Integer.parseInt(reader.readLine());
		MailSystem.newUser(name, username, born);
	}

	private static User logIn() throws IOException {
		String username;
		do {
			System.out.print("Username:\n>> ");
			username = reader.readLine();
		} while (!MailSystem.getExist(username));

		return user = MailSystem.getUser(username);
	}

	private static void asUser(User u) throws IOException {
		clearWindow();
		System.out.println(u);

		do {
			System.out.println("0 -> EXIT\t\t 1 -> Send mail");
			System.out.println("2 -> update\t\t 3 -> list");
			System.out.println("4 -> sort\t\t 5 -> filter");
			System.out.print(">> ");
			input = Integer.parseInt(reader.readLine());

			switch (input) {
				case 0: 
					return;
				//break;
				case 1:
					sendMail();
				break;
				case 2:
					update();
				break;
				case 3:
					list();
				break;
				case 4:
					sort();
				break;
				case 5:
					filter();
				break;
				default: System.out.println("ERROR");
			}
		} while(input != 0);

	}
	
	private static void sendMail(){}
	private static void update(){}
	private static void list(){}
	private static void sort(){}
	private static void filter(){}

	private static void clearWindow() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
}
