package p4.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SuppressWarnings("finally") // Finally block does not complete normally.
public class DynamicProxy implements InvocationHandler {
	private Object target = null;
	private boolean log = false;

	/**
	 * Constructor de DynamicProxy
	 * 
	 * @param target Instancia, de tipo Object.
	 * @param log    Booleano encargado de activar o desactivar el login de la
	 *               MailStore.
	 */
	private DynamicProxy(Object target, boolean log) {
		this.target = target;
		this.log = log;
	}

	/**
	 * Función que a partir de una instancia y el boolano log permite crear una
	 * nueva instancia.
	 * 
	 * @param target Instancia, de tipo Object.
	 * @param log    Booleano encargado de activar o desactivar el login de la
	 *               MailStore.
	 * @return Devuelve una nueva instancia, tipo Object.
	 */
	public static Object newInstance(Object target, boolean log) {
		Class<?> targetClass = target.getClass();
		Class<?>[] interfaces = targetClass.getInterfaces();

		return Proxy.newProxyInstance(targetClass.getClassLoader(), interfaces, new DynamicProxy(target, log));
	}

	/**
	 * Procesa una invocación de método en una instancia de proxy y devuelve el
	 * resultado.
	 * 
	 * @param proxy  La instancia de proxy en la que se invocó el método.
	 * @param method La instancia correspondiente al método de interfaz invocado en
	 *               la instancia de proxy.
	 * @param args   Una matriz de objetos que contiene los valores de los
	 *               argumentos pasados en la invocación del método en la instancia
	 *               del proxy.
	 * @return Devuelve un Object a partir de la invocación del método en la
	 *         instancia del proxy.
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object invocationResult = null;
		try {
			if (log) {
				System.out.println("Calling method: " + method.getName());
				Log.add("Calling method: " + method.getName());
				invocationResult = method.invoke(this.target, args);
				System.out.println("Ended method: " + method.getName());
				Log.add("Ended method: " + method.getName());
			} else {
				Log.add("Calling method: " + method.getName());
				invocationResult = method.invoke(this.target, args);
				Log.add("Ended method: " + method.getName());
			}
		} catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		} catch (Exception e) {
			System.err.println("Invocation of " + method.getName() + " failed");
		} finally {
			return invocationResult;
		}
	}

}
