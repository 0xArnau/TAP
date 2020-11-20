import system.*;
import users.*;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 

public class Cli {
	
	private static String name;
	private static String username;
	private static int born;
	private static BufferedReader reader =
		new BufferedReader(new InputStreamReader(System.in));
	private static int input;
	public static void main(String[] args) throws Exception {
		final User user;
		do {
			System.out.print(">>");
			username = reader.readLine();
		} while (!MailSystem.getExist(username));

		user = MailSystem.getUser(username);
		//check password

		do {
			System.out.println("0 -> EXIT");
			System.out.println("1 -> New User\t\t 2 -> ...");
			System.out.print(">>");
			input = Integer.parseInt(reader.readLine());

			switch (input) {
				case 0: 
				break;
				case 1:
				askData4NewUser();
				break;
				default: System.out.println("ERROR");
			}
		} while(input != 0);
	}

	private static void askData4NewUser() throws IOException {
		System.out.println("Name:");
		System.out.print(">>");
		name = reader.readLine();
		System.out.println("Username:");
		System.out.print(">>");
		username= reader.readLine();
		System.out.println("born:");
		System.out.print(">>");
		born = Integer.parseInt(reader.readLine());
		MailSystem.newUser(name, username, born);
	}
}
