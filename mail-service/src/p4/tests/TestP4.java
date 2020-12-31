package p4.tests;

import p4.mailstore.MailStoreAnnotation;
import p4.system.MailSystem;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import p1.mailstore.MailStore;
import p3.msfactory.MailStoreFactory;

public class TestP4 {
	public static void main(String[] args) throws Exception {
		Class<MailSystem> obj = MailSystem.class;

		if (obj.isAnnotationPresent(MailStoreAnnotation.class)) {
			Annotation annotation = obj.getAnnotation(MailStoreAnnotation.class);
			MailStoreAnnotation annot = (MailStoreAnnotation) annotation;

			System.out.printf("%nStore :%s", annot.store());
			System.out.printf("%nLog :%s", annot.log());
			System.out.println("\n");

			Class aClass = Class.forName(annot.store());
		}

		
		
	}
}
