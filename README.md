# TAP

## Mail Service


#### COMPILE

- make/make all

- make p1
	-	users
		- User.java
	- messages
		- Message.java
	- mailstore
		- MailStore.java
		- InMemory.java
		- OnFile.java
	- mailbox
		- MailBox.java
	- system
		- MailSystem.java
	- cli
		- Cli.java
	- tests
		- TestMailSystem.java
		- TestRunner.java
		- Test.java
		- TestSystem.java

- make p2
	- mailstore
		- MessageDecorator.java
		- EncodeDecorator.java
	- mailbox
		- AutomaticMailBox.java
		- Observer.java
		- SpamUserFilter.java
		- Subject.java
		- TooLongFilter.java
	- tests
		- TestDecorator.java
		- TestObserver.java
		- TestP2.java

- make p3
	- mailstore
		- RedisClient.java
		- RedisMailStore.java
		StoreAdapter.java
	- msfactory
		- FileFactory.java
		- MailStoreFactory.java
		- Memoryfactory.java
		- RedisFactory.java
	- system
		- MailSystemFactory.java
	- tests
		- TestP3.java

- make p4
	- dynamic
		- DynamicProxy.java
		- Log.java
	- mailstore
		- MailStoreAnnotation.java
	- system
		- MailSystem.java
	- test
		- testP4.java


#### RUN

- P1
	- make runtestp1
	- make runjunitp1
	- make runclip1	
	- make runtestsystemp1

- P2
	- make runtestp2
	- make runtestObserverp2
	- make runtestDecoratorp2

- P3
	- make runtestp3

- P4
	- make runtestp4
