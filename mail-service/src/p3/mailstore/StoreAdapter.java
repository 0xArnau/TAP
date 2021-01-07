package p3.mailstore;

import java.util.List;

import p1.messages.Message;
import p1.mailstore.*;


public class StoreAdapter implements MailStore {

	private RedisMailStore redis = null;

	/**
	 * Constructor de StoreAdapter
	 * @param redis redisMailStore
	 */
	public StoreAdapter(RedisMailStore redis) {
		this.redis = redis;
	}

	/**
	 * Acción alternativa que usa redis encargada de enviar un correo de un usuario a otro.
	 * 
	 * @param to Persona a la que irá a parar el correo.
	 * @param m  Variable de tipo Message (Consultar Message.java para más
	 *           información).
	 */
	@Override
	public void sendMail(String u, Message m) {
		redis.lpush(u, m.toString());
	}

	/**
	 * Función alternativa de getMail usando redis. Permite obtener los correos
	 * del buzón del usuario u.
	 * 
	 * @param u Usuario del que se quiere conseguir los correos.
	 * @return Devuelve una lista de los mensajes del usuario.
	 */
	@Override
	public List<Message> getMail(String u) throws Exception {
		return redis.lrange(u);
	}


	/**
	 * Borra todas las keys de todos las bases de datos. O(N) N = Nombre total de
	 * llaves
	 */
	public void clear() {
		redis.flushAll();
	}
}
