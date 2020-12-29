
P1: users messages mailstore

users: ./users/User.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 \
	-d ../../.out/mailservice/ ./users/User.java \
	-cp ../../.out/mailservice/
messages: ./messages/Message.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 \
	-d ../../.out/mailservice/ ./messages/Message.java \
	-cp ../../.out/mailservice/
mailstore: ./mailStore/MailStore.java ./mailStore/InMemory.java  ./mailStore/OnFile.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d ../../.out/mailservice/ ./mailStore/MailStore.java  	\
	-d ../../.out/mailservice/ ./mailStore/InMemory.java 		\
	-d ../../.out/mailservice/ ./mailStore/OnFile.java 			\
	-cp ../../.out/mailservice/
mailbox: ./mailBox/MailBox.java mailstore
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 \
	-d ../../.out/mailservice/ ./mailBox/MailBox.java \
	-cp ../../.out/mailservice/
system: mailbox ./system/MailSystem.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 \
	-d ../../.out/mailservice/ ./system/MailSystem.java \
	-cp ../../.out/mailservice/

