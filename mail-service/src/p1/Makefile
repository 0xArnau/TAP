all 		: build
build 	: users messages mailstore mailbox system main

junit : ../../lib/junit-4.13.1.jar ../../lib/hamcrest-core-1.3.jar
	cd ../../.out/mailservice/	 && \
	jar xf ../../lib/junit-4.13.1.jar org/ junit/	 && \
	jar xf ../../lib/hamcrest-core-1.3.jar org/ junit/	 && \
	cd ../../src/p1/

main 		: src/tests/Test.java src/cli/Cli.java src/tests/TestSystem.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
		-d ../../.out/mailservice/ src/tests/Test.java				\
		-d ../../.out/mailservice/ src/cli/Cli.java					\
		-d ../../.out/mailservice/ src/tests/TestSystem.java	\
		-cp ../../.out/mailservice/

system 	: mailbox src/system/MailSystem.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d ../../.out/mailservice/ src/system/MailSystem.java -cp ../../.out/mailservice/
mailbox : src/mailBox/MailBox.java mailstore
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d ../../.out/mailservice/ src/mailBox/MailBox.java -cp ../../.out/mailservice/
mailstore	: src/mailStore/MailStore.java src/mailStore/InMemory.java  src/mailStore/OnFile.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
		-d ../../.out/mailservice/ src/mailStore/MailStore.java  	\
		-d ../../.out/mailservice/ src/mailStore/InMemory.java 		\
		-d ../../.out/mailservice/ src/mailStore/OnFile.java 			\
		-cp ../../.out/mailservice/
messages 	: src/messages/Message.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d ../../.out/mailservice/ src/messages/Message.java -cp ../../.out/mailservice/
users 		: src/users/User.java
		javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 -d ../../.out/mailservice/ src/users/User.java -cp ../../.out/mailservice/

cjunit : ./src/tests/TestMailSystem.java ./src/tests/TestRunner.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
		-d ../../.out/mailservice/ src/tests/TestMailSystem.java 		\
		-d ../../.out/mailservice/ src/tests/TestRunner.java 			\
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