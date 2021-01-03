package p4.tests;

import p4.dynamic.Log;
import p4.system.MailSystem;

import p1.mailbox.MailBox;
import p1.messages.Message;
import p1.users.User;

public class TestP4 {
	public static void main(String[] args) throws Exception {
		/*
		 * Class<MailSystem> obj = MailSystem.class;
		 * 
		 * if (obj.isAnnotationPresent((Class<? extends Annotation>)
		 * MailStoreAnnotation.class)) { Annotation annotation =
		 * obj.getAnnotation(MailStoreAnnotation.class); MailStoreAnnotation annot =
		 * (MailStoreAnnotation) annotation;
		 * 
		 * System.out.printf("%nStore :%s", annot.store());
		 * System.out.printf("%nLog :%s", annot.log()); System.out.println("\n"); }
		 * 
		 * Class<?> [] arg = new Class[1]; arg[0] = String.class; Class<?> aClass =
		 * Class.forName("p1.mailstore.OnFile"); java.lang.reflect.Method nameMethod =
		 * aClass.getDeclaredMethod("getMail", arg); Object newObject =
		 * aClass.getDeclaredConstructor().newInstance(); String output = (String)
		 * nameMethod.invoke(newObject, "star");
		 * 
		 * System.out.println(output);
		 * 
		 * for (java.lang.reflect.Method method : aClass.getDeclaredMethods()) {
		 * System.out.print(method.getName() + " ");
		 * System.out.print(method.getParameterTypes() + " ");
		 * System.out.print(method.getDeclaringClass() + " ");
		 * System.out.println(method.getReturnType()); }
		 */
		MailSystem ms = new MailSystem();
		MailBox mb = ms.newUser(new User("star", "arnau", 2000));
		System.out.println("Enviando correo...");
		mb.sendMail("star", new Message("star", "star", "subject", "body"));
		System.out.println("Actualizando correos...");
		mb.updateMail().forEach(System.out::println);
		System.out.println("Printando log: ");
		Log.getLogs().forEach(System.out::println);

	}
}
