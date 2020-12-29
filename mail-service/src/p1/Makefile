all 		: build
build 	: users messages mailstore mailbox system main

junit : ../../lib/junit-4.13.1.jar ../../lib/hamcrest-core-1.3.jar
	cd ../../.out/mailservice/	 && \
	jar xf ../../lib/junit-4.13.1.jar \
		org/ junit/ &&\
	jar xf ../../lib/hamcrest-core-1.3.jar org/hamcrest/ &&\
	cd ../../src/p1/



main 		: ./tests/Test.java ./cli/Cli.java ./tests/TestSystem.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
		-d ../../.out/mailservice/ ./tests/Test.java				\
		-d ../../.out/mailservice/ ./cli/Cli.java					\
		-d ../../.out/mailservice/ ./tests/TestSystem.java	\
		-cp ../../.out/mailservice/

system 	: mailbox ./system/MailSystem.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d ../../.out/mailservice/ ./system/MailSystem.java -cp ../../.out/mailservice/
mailbox : ./mailBox/MailBox.java mailstore
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d ../../.out/mailservice/ ./mailBox/MailBox.java -cp ../../.out/mailservice/
mailstore	: ./mailStore/MailStore.java ./mailStore/InMemory.java  ./mailStore/OnFile.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
		-d ../../.out/mailservice/ ./mailStore/MailStore.java  	\
		-d ../../.out/mailservice/ ./mailStore/InMemory.java 		\
		-d ../../.out/mailservice/ ./mailStore/OnFile.java 			\
		-cp ../../.out/mailservice/
messages 	: ./messages/Message.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d ../../.out/mailservice/ ./messages/Message.java -cp ../../.out/mailservice/
users 		: ./users/User.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d ../../.out/mailservice/ ./users/User.java -cp ../../.out/mailservice/

cjunit : ./tests/TestMailSystem.java ./tests/TestRunner.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
		-d ../../.out/mailservice/ ./tests/TestMailSystem.java 		\
		-d ../../.out/mailservice/ ./tests/TestRunner.java 			\
		-cp ../../.out/mailservice/

rjunit : ../../.out/mailservice/p1/tests/TestMailSystem.class ../../.out/mailservice/p1/tests/TestRunner.class
	java -cp ../../.out/mailservice/ p1.tests.TestRunner

test		: ../../.out/mailservice/p1/tests/Test.class
		java -cp ../../.out/mailservice/ p1.tests.Test
testsystem	: ../../.out/mailservice/p1/tests/TestSystem.class
		java -cp ../../.out/mailservice/ p1.tests.TestSystem
cli		: ../../.out/mailservice/cli/p1/Cli.class
		java -cp ../../.out/mailservice/ p1.cli.Cli
clean 		: ../../.out/
		rm -r ../../.out/

create	:
	mkdir ../../.out/ && mkdir ../../.out/mailservice/