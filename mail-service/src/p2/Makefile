build: mailstore mailbox  main

main:	./tests/TestP2.java ./tests/TestObserver.java ./tests/TestDecorator.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d ../../.out/mailservice/ ./tests/TestP2.java	\
	-d ../../.out/mailservice/ ./tests/TestObserver.java	\
	-d ../../.out/mailservice/ ./tests/TestDecorator.java 	\
	-cp ../../.out/mailservice/

mailstore	: ../../.out/mailservice/p1/mailstore/MailStore.class	\
	../../.out/mailservice/p1/mailstore/InMemory.class	\
	../../.out/mailservice/p1/mailstore/OnFile.class	\
	./mailstore/MessageDecorator.java \
	./mailstore/EncodeDecorator.java 
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d ../../.out/mailservice/ 	./mailstore/MessageDecorator.java	\
	-d ../../.out/mailservice/  ./mailstore/EncodeDecorator.java	\
	-cp ../../.out/mailservice/
mailbox	: ../../.out/mailservice/p1/mailbox/mailBox.class	\
	./mailbox/AutomaticMailBox.java	\
	./mailbox/SpamUserFilter.java	\
	./mailbox/TooLongFilter.java	\
	./mailbox/Observer.java	\
	./mailbox/Subject.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d ../../.out/mailservice/ ./mailbox/Observer.java	\
	-d ../../.out/mailservice/ ./mailbox/Subject.java	\
	-d ../../.out/mailservice/ ./mailBox/AutomaticMailBox.java	\
	-d ../../.out/mailservice/ ./mailBox/SpamUserFilter.java	\
	-d ../../.out/mailservice/ ./mailBox/TooLongFilter.java	\
	-cp ../../.out/mailservice/


test		: ../../.out/mailservice/p2/tests/TestP2.class
	java -cp ../../.out/mailservice/ p2.tests.TestP2

testObserver		: ../../.out/mailservice/p2/tests/TestObserver.class
	java -cp ../../.out/mailservice/ p2.tests.TestObserver

testDecorator		: ../../.out/mailservice/p2/tests/TestDecorator.class
	java -cp ../../.out/mailservice/ p2.tests.TestDecorator