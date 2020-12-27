build:  main

basic : users messages mailstore mailbox system

jedis : ../lib/jedis-2.9.0.jar
	cd ../.out/mailservice/	 &&\
	jar xf ../../lib/jedis-2.9.0.jar redis/	 &&\
	cd ../../p3/

main:	src/TestP3.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d ../.out/mailservice/ src/TestP3.java	\
	-cp ../.out/mailservice/


mailstore	: ../p1/src/mailStore/MailStore.java	\
	../p1/src/mailStore/InMemory.java	\
	../p1/src/mailStore/OnFile.java	\
	./src/mailstore/MessageDecorator.java \
	./src/mailstore/EncodeDecorator.java 
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d ../.out/mailservice/ ../p1/src/mailStore/MailStore.java  	\
	-d ../.out/mailservice/ ../p1/src/mailStore/InMemory.java 		\
	-d ../.out/mailservice/ ../p1/src/mailStore/OnFile.java 			\
	-d ../.out/mailservice/  src/mailstore/MessageDecorator.java	\
	-d ../.out/mailservice/  src/mailstore/EncodeDecorator.java	\
	-cp ../.out/mailservice/


test		: ../.out/mailservice/TestP3.class
	java -cp ../.out/mailservice/ TestP3
