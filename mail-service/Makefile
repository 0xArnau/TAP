.CLASS = .out/mailservice/



all: create lib p1 p2 p3 p4

clean: .out/
	rm -r .out/mailservice/p1 && rm -r .out/mailservice/p2 && rm -r .out/mailservice/p3

create:
	[ -d $(.CLASS) ] || mkdir -p $(.CLASS)

lib: lib/junit-4.13.1.jar lib/hamcrest-core-1.3.jar lib/jedis-2.9.0.jar

	cd .out/mailservice/	 && \
	jar xf ../../lib/junit-4.13.1.jar \
		org/junit/ &&\
	jar xf ../../lib/hamcrest-core-1.3.jar org/hamcrest/ &&\
	jar xf ../../lib/jedis-2.9.0.jar redis/clients/ &&\
	cd ../../

p1: users messages mailstore mailbox system cli testsp1 cjunitp1

users: src/p1/users/User.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 \
	-d .out/mailservice/ src/p1/users/User.java \
	-cp .out/mailservice/

messages: src/p1/messages/Message.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 \
	-d .out/mailservice/ src/p1/messages/Message.java \
	-cp .out/mailservice/

mailstore: src/p1/mailStore/MailStore.java \
	src/p1/mailStore/InMemory.java \
	src/p1/mailStore/OnFile.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d .out/mailservice/ src/p1/mailStore/MailStore.java  	\
	-d .out/mailservice/ src/p1/mailStore/InMemory.java 		\
	-d .out/mailservice/ src/p1/mailStore/OnFile.java 			\
	-cp .out/mailservice/

mailbox: src/p1/mailBox/MailBox.java mailstore

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 \
	-d .out/mailservice/ src/p1/mailBox/MailBox.java \
	-cp .out/mailservice/

system: mailbox src/p1/system/MailSystem.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 \
	-d .out/mailservice/ src/p1/system/MailSystem.java \
	-cp .out/mailservice/

testsp1: src/p1/tests/Test.java src/p1/tests/TestSystem.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d .out/mailservice/ src/p1/tests/Test.java				\
	-d .out/mailservice/ src/p1/tests/TestSystem.java	\
	-cp .out/mailservice/

cli: src/p1/cli/Cli.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d .out/mailservice/ src/p1/cli/Cli.java					\
	-cp .out/mailservice/

cjunitp1: src/p1/tests/TestMailSystem.java src/p1/tests/TestRunner.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
		-d .out/mailservice/ src/p1/tests/TestMailSystem.java 		\
		-d .out/mailservice/ src/p1/tests/TestRunner.java 			\
		-cp .out/mailservice/


runtestp1: .out/mailservice/p1/tests/Test.class

	java -cp .out/mailservice/ p1.tests.Test

runjunitp1: .out/mailservice/p1/tests/TestMailSystem.class .out/mailservice/p1/tests/TestRunner.class

	java -cp .out/mailservice/ p1.tests.TestRunner

runclip1: .out/mailservice/cli/p1/Cli.class

		java -cp .out/mailservice/ p1.cli.Cli

runtestsystemp1: .out/mailservice/p1/tests/TestSystem.class

	java -cp ..out/mailservice/ p1.tests.TestSystem

p2: mailstorep2 mailboxp2 testsp2

mailstorep2	: .out/mailservice/p1/mailstore/MailStore.class	\
	.out/mailservice/p1/mailstore/InMemory.class	\
	.out/mailservice/p1/mailstore/OnFile.class	\
	src/p2/mailstore/MessageDecorator.java \
	src/p2/mailstore/EncodeDecorator.java 

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d .out/mailservice/ 	src/p2/mailstore/MessageDecorator.java	\
	-d .out/mailservice/  src/p2/mailstore/EncodeDecorator.java	\
	-cp .out/mailservice/

mailboxp2	: .out/mailservice/p1/mailbox/mailBox.class	\
	src/p2/mailbox/AutomaticMailBox.java	\
	src/p2/mailbox/SpamUserFilter.java	\
	src/p2/mailbox/TooLongFilter.java	\
	src/p2/mailbox/Observer.java	\
	src/p2/mailbox/Subject.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d .out/mailservice/ src/p2/mailbox/Observer.java	\
	-d .out/mailservice/ src/p2/mailbox/Subject.java	\
	-d .out/mailservice/ src/p2/mailBox/AutomaticMailBox.java	\
	-d .out/mailservice/ src/p2/mailBox/SpamUserFilter.java	\
	-d .out/mailservice/ src/p2/mailBox/TooLongFilter.java	\
	-cp .out/mailservice/

testsp2 : src/p2/tests/TestP2.java \
	src/p2/tests/TestObserver.java \
	src/p2/tests/TestDecorator.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d ..out/mailservice/ src/p2/tests/TestP2.java	\
	-d .out/mailservice/ src/p2/tests/TestObserver.java	\
	-d .out/mailservice/ src/p2/tests/TestDecorator.java 	\
	-cp .out/mailservice/

runtestp2: .out/mailservice/p2/tests/TestP2.class

	java -cp .out/mailservice/ p2.tests.TestP2

runtestObserverp2: .out/mailservice/p2/tests/TestObserver.class

	java -cp .out/mailservice/ p2.tests.TestObserver

runtestDecoratorp2: .out/mailservice/p2/tests/TestDecorator.class

	java -cp .out/mailservice/ p2.tests.TestDecorator

p3: mailstorep3 msfactoryp3 mssystemp3 testsp3

mailstorep3	: .out/mailservice/p1/mailstore/MailStore.class	\
	.out/mailservice/p1/mailstore/InMemory.class	\
	.out/mailservice/p1/mailstore/OnFile.class	\
	.out/mailservice/p2/mailstore/MessageDecorator.class	\
	.out/mailservice/p2/mailstore/EncodeDecorator.class	\
	src/p3/mailstore/RedisMailStore.java \
	src/p3/mailstore/RedisClient.java \
	src/p3/mailstore/StoreAdapter.java 

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d .out/mailservice/  src/p3/mailstore/RedisMailStore.java	\
	-d .out/mailservice/  src/p3/mailstore/RedisClient.java	\
	-d .out/mailservice/  src/p3/mailstore/StoreAdapter.java	\
	-cp .out/mailservice/

msfactoryp3 : src/p3/msfactory/MailStoreFactory.java	\
	src/p3/msfactory/MemoryFactory.java	\
	src/p3/msfactory/RedisFactory.java	\
	src/p3/msfactory/FileFactory.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d .out/mailservice/  src/p3/msfactory/FileFactory.java	\
	-d .out/mailservice/  src/p3/msfactory/RedisFactory.java	\
	-d .out/mailservice/  src/p3/msfactory/MemoryFactory.java	\
	-d .out/mailservice/  src/p3/msfactory/MailStoreFactory.java	\
	-cp .out/mailservice/

mssystemp3 : src/p3/system/MailSystemFactory.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8 	\
	-d .out/mailservice/  src/p3/system/MailSystemFactory.java	\
	-cp .out/mailservice/

testsp3: src/p3/tests/TestP3.java

	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d .out/mailservice/ src/p3/tests/TestP3.java	\
	-cp .out/mailservice/

runtestp3		: .out/mailservice/p3/tests/TestP3.class
	java -cp .out/mailservice/ p3.tests/TestP3


p4: dynamic mailstorep4 systemp4 testsp4

mailstorep4: ./src/p4/mailstore/MailStoreAnnotation.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d .out/mailservice/ src/p4/mailstore/MailStoreAnnotation.java	\
	-cp .out/mailservice/

systemp4: ./src/p4/system/MailSystem.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d .out/mailservice/ src/p4/system/MailSystem.java	\
	-cp .out/mailservice/

dynamic: ./src/p4/dynamic/DynamicProxy.java ./src/p4/dynamic/Log.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d .out/mailservice/ src/p4/dynamic/Log.java	\
	-d .out/mailservice/ src/p4/dynamic/DynamicProxy.java	\
	-cp .out/mailservice/

testsp4: ./src/p4/tests/TestP4.java
	javac -Xdiags:verbose -Xlint:unchecked -encoding UTF-8	\
	-d .out/mailservice/ src/p4/tests/Testp4.java	\
	-cp .out/mailservice/

runtestp4: .out/mailservice/p4/tests/TestP4.class
	java -cp .out/mailservice/ p4.tests/TestP4