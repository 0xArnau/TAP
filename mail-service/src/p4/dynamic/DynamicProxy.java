package p4.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
	private Object target = null;

	private DynamicProxy(Object target) {
		this.target = target;
	}

	public static Object newInstance(Object target) {
		Class targetClass = target.getClass();
		Class [] interfaces = targetClass.getInterfaces();
		return Proxy.newProxyInstance(targetClass.getClassLoader()
			, interfaces
			, new DynamicProxy(target));
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object invocationResult = null;
		try
		{
			System.out.println("Calling method: " + method.getName());
			invocationResult = method.invoke(this.target, args);
			System.out.println("Ended method: " + method.getName());
		}
		catch(InvocationTargetException ite)
		{
				 throw ite.getTargetException();
		}
		catch(Exception e)
		{
				System.err.println("Invocation of " + method.getName() + " failed");
		}
		finally{
				return invocationResult;
		}
	}
	
}
