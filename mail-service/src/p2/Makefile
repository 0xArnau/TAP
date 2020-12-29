build: mailstore mailbox  main

main:	src/tests/TestP2.java src/tests/TestObserver.java src/tests/TestDecorator.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d ../../.out/mailservice/ src/tests/TestP2.java	\
	-d ../../.out/mailservice/ src/tests/TestObserver.java	\
	-d ../../.out/mailservice/ src/tests/TestDecorator.java 	\
	-cp ../../.out/mailservice/

mailstore	: ../../.out/mailservice/p1/src/mailstore/MailStore.class	\
	../../.out/mailservice/p1/src/mailstore/InMemory.class	\
	../../.out/mailservice/p1/src/mailstore/OnFile.class	\
	./src/mailstore/MessageDecorator.java \
	./src/mailstore/EncodeDecorator.java 
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d ../../.out/mailservice/ -sourcepath .././	src/mailstore/MessageDecorator.java	\
	-d ../../.out/mailservice/ -sourcepath .././ src/mailstore/EncodeDecorator.java	\
	-cp ../../.out/mailservice/
mailbox	: ../../.out/mailservice/p1/src/mailbox/mailBox.class	\
	./src/mailbox/AutomaticMailBox.java	\
	./src/mailbox/SpamUserFilter.java	\
	./src/mailbox/TooLongFilter.java	\
	./src/mailbox/Observer.java	\
	./src/mailbox/Subject.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d ../../.out/mailservice/ src/mailbox/Observer.java	\
	-d ../../.out/mailservice/ src/mailbox/Subject.java	\
	-d ../../.out/mailservice/ ./src/mailBox/AutomaticMailBox.java	\
	-d ../../.out/mailservice/ ./src/mailBox/SpamUserFilter.java	\
	-d ../../.out/mailservice/ ./src/mailBox/TooLongFilter.java	\
	-cp ../../.out/mailservice/


test		: ../../.out/mailservice/p2/src/tests/TestP2.class
	java -cp ../../.out/mailservice/ p2.src.tests.TestP2

testObserver		: ../../.out/mailservice/p2/src/tests/TestObserver.class
	java -cp ../../.out/mailservice/ p2.src.tests.TestObserver

testDecorator		: ../../.out/mailservice/p2/src/tests/TestDecorator.class
	java -cp ../../.out/mailservice/ p2.src.tests.TestDecorator