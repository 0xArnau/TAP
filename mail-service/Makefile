all 		: build run
build 		: main mailbox mailstore messages users	
main 		: src/Main.java users messages mailstore
		javac -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/Main.java -cp .out/mailservice/
mailbox 	: src/mailBox/MailBox.java
		javac -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/mailBox/MailBox.java -cp .out/mailservice/
mailstore 	: src/mailStore/MailStore.java users messages
		javac -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/mailStore/MailStore.java -cp .out/mailservice/
messages 	: src/messages/Message.java
		javac -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/messages/Message.java -cp .out/mailservice/
users 		: src/users/User.java
		javac -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/users/User.java -cp .out/mailservice/
run 		: .out/mailservice/Main.class
		java -cp .out/mailservice/ Main
clean 		: .out/mailservice/
		rm -r .out/mailservice/