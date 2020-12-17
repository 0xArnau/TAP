all 		: build
build 	: users messages mailstore mailbox system main

main 		: src/Test.java src/Cli.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
		-d .out/mailservice/ src/Test.java			\
		-d .out/mailservice/ src/Cli.java			\
		-cp .out/mailservice/

system 	: mailbox src/system/MailSystem.java users messages mailstore mailbox
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/system/MailSystem.java -cp .out/mailservice/
mailbox : src/mailBox/MailBox.java mailstore
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/mailBox/MailBox.java -cp .out/mailservice/
mailstore	: src/mailStore/MailStore.java src/mailStore/InMemory.java  src/mailStore/OnFile.java messages
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
		-d .out/mailservice/ src/mailStore/MailStore.java  	\
		-d .out/mailservice/ src/mailStore/InMemory.java 		\
		-d .out/mailservice/ src/mailStore/OnFile.java 			\
		-cp .out/mailservice/
messages 	: src/messages/Message.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/messages/Message.java -cp .out/mailservice/
users 		: src/users/User.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d .out/mailservice/ src/users/User.java -cp .out/mailservice/

test		: .out/mailservice/Test.class
		java -cp .out/mailservice/ Test
cli		: .out/mailservice/Cli.class
		java -cp .out/mailservice/ Cli
clean 		: .out/mailservice/
		rm -r .out/mailservice/