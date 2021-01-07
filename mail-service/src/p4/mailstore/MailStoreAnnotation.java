package p4.mailstore;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MailStoreAnnotation {
	String store() default "p1.mailstore.InMemory";
	boolean log() default false;
}
