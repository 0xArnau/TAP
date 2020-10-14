all 		: build run
build 		: main mailbox mailstore messages users	
main 		: src/Main.java users
		javac -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/Main.java -cp .out/mailservice/
mailbox 	: src/mailBox/Mailbox.java
		javac -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/mailBox/Mailbox.java -cp .out/mailservice/
mailstore 	: src/mailStore/Mailstore.java
		javac -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/mailStore/Mailstore.java -cp .out/mailservice/
messages 	: src/messages/Message.java
		javac -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/messages/Message.java -cp .out/mailservice/
users 		: src/users/User.java
		javac -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/users/User.java -cp .out/mailservice/
run 		: .out/mailservice/Main.class
		java -cp .out/mailservice/ Main
clean 		: .out/mailservice/
		rm -r .out/mailservice/