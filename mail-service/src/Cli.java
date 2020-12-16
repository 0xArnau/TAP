import system.*;
import messages.*;
import mailstore.*;
import mailbox.*;
import users.*;


import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 

public class Cli {
	User user;
	private static BufferedReader reader =
		new BufferedReader(new InputStreamReader(System.in));
	private static enum Commands {
		createuser,
		filter,
		logas,
		send,
		update,
		list,
		sort,
	}
	public static void main(String[] args) {
		nonUsers();
	}
	private static void nonUsers() {
		String [] info;
		info = waiting4commandNonUsers();
		switch (info[0]) {
			case "creatuser":	
			break;
			case "filter":
			break;
			case "logas":
			break;
			default:
			System.out.println("ERROR\nTry again:");
			nonUsers();
			break;
		}
	}
	private static String [] waiting4commandNonUsers() {
		
		try {
			System.out.println("Commands availables 4 non users:");
			System.out.println("\t" + Commands.createuser + " <username><args>...");
			System.out.println("\t" + Commands.filter + " <contains<word>> <lessthan<n words>>");
			System.out.println("\t" + Commands.logas + " <username>");
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

	private static void users() {
		String [] info;
		info = waiting4commandNonUsers();
		switch (info[0]) {
			case "creatuser":	
			break;
			case "filter":
			break;
			case "logas":
			break;
			default:
			break;
		}
	}
	private static String [] waiting4commandUsers() {
		
		try {
			System.out.println("Commands availables 4 users:");
			System.out.println("\t" + Commands.filter + " <contains<word>> <lessthan<n words>>");
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
