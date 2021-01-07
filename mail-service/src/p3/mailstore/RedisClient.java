package p3.mailstore;

import java.util.LinkedList;
import java.util.List;

import p1.messages.Message;

public class RedisClient implements RedisMailStore {

	private static RedisClient instance = null;

	private RedisClient() {}

	/**
	 * Función que devuelve la instancia de un RedisClient.
	 * 
	 * @return Devuelve la instancia de un RedisClient
	 */
	public static RedisClient getInstance() {
		if (RedisClient.instance == null) {
			RedisClient.instance = new RedisClient();
		}
		return RedisClient.instance;
	}

	/**
	 * Inserta los valores especificados en el inicio de la lista guardada en la
	 * llave. O(1) por cada elemento añadido
	 * 
	 * @param u Llave a la que se le quiere añadir valor.
	 * @param m Valor que se quiere añadir a la lista de la llave.
	 */
	@Override
	public void lpush(String u, String m) {
		jedis.lpush(u, m);
	}

	/**
	 * Devuelve los elementos de la lista guardados en la llave u.
	 * 
	 * @param u Llave de la que se quieren los elementos.
	 * @return Devuelve una lista con todos los elementos de la llave.
	 */
	@Override
	public List<Message> lrange(String u) {
		List<Message> list = new LinkedList<Message>();
		jedis.lrange(u, 0, -1).forEach((e) -> {
			list.add(new Message(e));
		});
		return list;
	}

	/**
	 * Borra todas las keys de todos las bases de datos. O(N) N = Nombre total de
	 * llaves
	 */
	@Override
	public void flushAll() {
		jedis.flushAll();
	}
}
