build:   mailstore msfactory mssystem main

basic : users messages mailstore mailbox system

jedis : ../../lib/jedis-2.9.0.jar
	cd ../../.out/mailservice/	 &&\
	jar xf ../../lib/jedis-2.9.0.jar redis/clients/ &&\
	cd ../../src/p3/

main:	./tests/TestP3.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d ../../.out/mailservice/ ./tests/TestP3.java	\
	-cp ../../.out/mailservice/

mailstore	: ../../.out/mailservice/p1/mailstore/MailStore.class	\
	../../.out/mailservice/p1/mailstore/InMemory.class	\
	../../.out/mailservice/p1/mailstore/OnFile.class	\
	../../.out/mailservice/p2/mailstore/MessageDecorator.class	\
	../../.out/mailservice/p2/mailstore/EncodeDecorator.class	\
	././mailstore/RedisMailStore.java \
	././mailstore/RedisClient.java \
	././mailstore/StoreAdapter.java 
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d ../../.out/mailservice/  ./mailstore/RedisMailStore.java	\
	-d ../../.out/mailservice/  ./mailstore/RedisClient.java	\
	-d ../../.out/mailservice/  ./mailstore/StoreAdapter.java	\
	-cp ../../.out/mailservice/

msfactory : ././msfactory/MailStoreFactory.java	\
	 ././msfactory/MemoryFactory.java	\
	 ././msfactory/RedisFactory.java	\
	 ././msfactory/FileFactory.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d ../../.out/mailservice/  ././msfactory/FileFactory.java	\
	-d ../../.out/mailservice/  ././msfactory/RedisFactory.java	\
	-d ../../.out/mailservice/  ././msfactory/MemoryFactory.java	\
	-d ../../.out/mailservice/  ././msfactory/MailStoreFactory.java	\
	-cp ../../.out/mailservice/

mssystem : ././system/MailSystemFactory.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d ../../.out/mailservice/  ././system/MailSystemFactory.java	\
	-cp ../../.out/mailservice/
test		: ../../.out/mailservice/p3/tests/TestP3.class
	java -cp ../../.out/mailservice/ p3.tests/TestP3
