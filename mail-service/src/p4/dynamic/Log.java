package p4.dynamic;

import java.util.ArrayList;
import java.util.List;

public class Log {
	private static List<String> logs = new ArrayList<String>();

	/**
	 * Acción que permite añadir un string a la lista de logs.
	 * 
	 * @param s String a añadir a la lista.
	 */
	public static void add(String s) {
		logs.add(s);
	}

	/**
	 * Función encargada de devolver una lista de los logs.
	 * 
	 * @return Devuelve una lista con todos los logs.
	 */
	public static List<String> getLogs() {
		return logs;
	}

}
