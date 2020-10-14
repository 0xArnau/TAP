all 		: build run
build 		: main mailbox mailstore messages users	
main 		: src/Main.java
		javac -Xlint:unchecked -d classes/ src/Main.java
mailbox 	: src/MailBox/Mailbox.java
		javac -Xlint:unchecked -d classes/ src/MailBox/Mailbox.java
mailstore 	: src/MailStore/Mailstore.java
		javac -Xlint:unchecked -d classes/ src/MailStore/Mailstore.java
messages 	: src/Messages/Message.java
		javac -Xlint:unchecked -d classes/ src/Messages/Message.java
users 		:  src/Users/User.java
		javac -Xlint:unchecked -d classes/ src/Users/User.java
run 		: classes/Main.class
		java -cp classes Main
clean 		: classes/
		rm -r classes/