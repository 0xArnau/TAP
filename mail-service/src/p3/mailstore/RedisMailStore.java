package p3.mailstore;

import java.util.List;

import p1.messages.Message;
import redis.clients.jedis.Jedis;

public interface RedisMailStore {
	final Jedis jedis = new Jedis("localhost");

	/**
	 * Inserta los valores especificados en el inicio de la lista guardada en la
	 * llave. O(1) por cada elemento añadido
	 * 
	 * @param u Llave a la que se le quiere añadir valor.
	 * @param m Valor que se quiere añadir a la lista de la llave.
	 */
	public void lpush(String u, String m);

	/**
	 * Devuelve los elementos de la lista guardados en la llave u.
	 * 
	 * @param u Llave de la que se quieren los elementos.
	 * @return Devuelve una lista con todos los elementos de la llave.
	 */
	public List<Message> lrange(String u);

	/**
	 * Borra todas las keys de todos las bases de datos. O(N) N = Nombre total de
	 * llaves
	 */
	public void flushAll();
}
