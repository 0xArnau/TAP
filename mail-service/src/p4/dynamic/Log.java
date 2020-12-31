package p4.dynamic;

import java.util.ArrayList;
import java.util.List;

public class Log {
	private static List<String> logs = new ArrayList<String>();

	public static void add(String s) {
		logs.add(s);
	}

	public static List<String> getLogs() {
		return logs;
	}

}
