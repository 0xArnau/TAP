build: basic  main

basic : users messages mailstore mailbox system

main:	src/TestP2.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d .out/mailservice/ src/TestP2.java	\
	-d .out/mailservice/ src/TestObserver.java	\
	-d .out/mailservice/ src/TestDecorator.java	\
	-cp .out/mailservice/



users	: ../p1/src/users/User.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d .out/mailservice/ ../p1/src/users/User.java	\
	-cp .out/mailservice/
messages	: ../p1/src/messages/Message.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d .out/mailservice/ ../p1/src/messages/Message.java	\
	-cp .out/mailservice/
mailstore	: ../p1/src/mailStore/MailStore.java	\
	../p1/src/mailStore/InMemory.java	\
	../p1/src/mailStore/OnFile.java	\
	./src/mailstore/MessageDecorator.java \
	./src/mailstore/EncodeDecorator.java 
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d .out/mailservice/ ../p1/src/mailStore/MailStore.java  	\
	-d .out/mailservice/ ../p1/src/mailStore/InMemory.java 		\
	-d .out/mailservice/ ../p1/src/mailStore/OnFile.java 			\
	-d .out/mailservice/  src/mailstore/MessageDecorator.java	\
	-d .out/mailservice/  src/mailstore/EncodeDecorator.java	\
	-cp .out/mailservice/
mailbox	: ../p1/src/mailBox/MailBox.java	\
	./src/mailbox/AutomaticMailBox.java	\
	./src/mailbox/SpamUserFilter.java	\
	./src/mailbox/TooLongFilter.java	\
	./src/mailbox/Observer.java	\
	./src/mailbox/Subject.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d	.out/mailservice/ ../p1/src/mailBox/MailBox.java	\
	-d .out/mailservice/ src/mailbox/Observer.java	\
	-d .out/mailservice/ src/mailbox/Subject.java	\
	-d .out/mailservice/ ./src/mailBox/AutomaticMailBox.java	\
	-d .out/mailservice/ ./src/mailBox/SpamUserFilter.java	\
	-d .out/mailservice/ ./src/mailBox/TooLongFilter.java	\
	-cp .out/mailservice/
system	: ../p1/src/system/MailSystem.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d .out/mailservice/  ../p1/src/system/MailSystem.java	\
	-cp .out/mailservice/

test		: .out/mailservice/TestP2.class
	java -cp .out/mailservice/ TestP2

testObserver		: .out/mailservice/TestObserver.class
	java -cp .out/mailservice/ TestObserver

testDecorator		: .out/mailservice/TestDecorator.class
	java -cp .out/mailservice/ TestDecorator