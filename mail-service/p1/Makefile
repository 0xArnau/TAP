all 		: build
build 	: users messages mailstore mailbox system main

junit : ../lib/junit-4.13.1.jar ../lib/hamcrest-core-1.3.jar
	cd ../.out/mailservice/	 && \
	jar xf ../../lib/junit-4.13.1.jar org/ junit/	 && \
	jar xf ../../lib/hamcrest-core-1.3.jar org/ junit/	 && \
	cd ../../p1/

main 		: src/Test.java src/Cli.java src/TestSystem.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
		-d ../.out/mailservice/ src/Test.java				\
		-d ../.out/mailservice/ src/Cli.java					\
		-d ../.out/mailservice/ src/TestSystem.java	\
		-cp ../.out/mailservice/

system 	: mailbox src/system/MailSystem.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d ../.out/mailservice/ src/system/MailSystem.java -cp ../.out/mailservice/
mailbox : src/mailBox/MailBox.java mailstore
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d ../.out/mailservice/ src/mailBox/MailBox.java -cp ../.out/mailservice/
mailstore	: src/mailStore/MailStore.java src/mailStore/InMemory.java  src/mailStore/OnFile.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
		-d ../.out/mailservice/ src/mailStore/MailStore.java  	\
		-d ../.out/mailservice/ src/mailStore/InMemory.java 		\
		-d ../.out/mailservice/ src/mailStore/OnFile.java 			\
		-cp ../.out/mailservice/
messages 	: src/messages/Message.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d ../.out/mailservice/ src/messages/Message.java -cp ../.out/mailservice/
users 		: src/users/User.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d ../.out/mailservice/ src/users/User.java -cp ../.out/mailservice/

cjunit : ./src/TestMailSystem.java ./src/TestRunner.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
		-d ../.out/mailservice/ src/TestMailSystem.java 		\
		-d ../.out/mailservice/ src/TestRunner.java 			\
		-cp ../.out/mailservice/

rjunit : ../.out/mailservice/TestMailSystem.class ../.out/mailservice/TestRunner.class
	java -cp ../.out/mailservice/ TestRunner

test		: ../.out/mailservice/Test.class
		java -cp ../.out/mailservice/ Test
testsystem	: ../.out/mailservice/TestSystem.class
		java -cp ../.out/mailservice/ TestSystem
cli		: ../.out/mailservice/Cli.class
		java -cp ../.out/mailservice/ Cli
clean 		: ../.out/mailservice/
		rm -r ../.out/mailservice/