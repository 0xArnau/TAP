build:   mailstore main

basic : users messages mailstore mailbox system

jedis : ../lib/jedis-2.9.0.jar
	cd ../.out/mailservice/	 &&\
	jar xf ../../lib/jedis-2.9.0.jar redis/	 &&\
	cd ../../p3/

main:	src/TestP3.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d ../.out/mailservice/ src/TestP3.java	\
	-cp ../.out/mailservice/

users	: ../p1/src/users/User.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d ../.out/mailservice/ ../p1/src/users/User.java	\
	-cp ../.out/mailservice/
messages	: ../p1/src/messages/Message.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d ../.out/mailservice/ ../p1/src/messages/Message.java	\
	-cp ../.out/mailservice/
mailstore	: ../p1/src/mailStore/MailStore.java	\
	../p1/src/mailStore/InMemory.java	\
	../p1/src/mailStore/OnFile.java	\
	../p2/./src/mailstore/MessageDecorator.java \
	../p2/./src/mailstore/EncodeDecorator.java  \
	./src/mailstore/RedisMailStore.java \
	./src/mailstore/RedisStore.java \
	./src/mailstore/StoreAdapter.java 
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d ../.out/mailservice/ ../p1/src/mailStore/MailStore.java  	\
	-d ../.out/mailservice/ ../p1/src/mailStore/InMemory.java 		\
	-d ../.out/mailservice/ ../p1/src/mailStore/OnFile.java 			\
	-d ../.out/mailservice/ ../p2/src/mailstore/MessageDecorator.java	\
	-d ../.out/mailservice/ ../p2/src/mailstore/EncodeDecorator.java	\
	-d ../.out/mailservice/  src/mailstore/RedisMailStore.java	\
	-d ../.out/mailservice/  src/mailstore/RedisStore.java	\
	-d ../.out/mailservice/  src/mailstore/StoreAdapter.java	\
	-cp ../.out/mailservice/


test		: ../.out/mailservice/TestP3.class
	java -cp ../.out/mailservice/ TestP3
