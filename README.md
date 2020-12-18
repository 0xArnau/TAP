# TAP

### Mail Service

- 1: Initialize the mail system with an in-memory mail store    (MailSystem es quien decide si es OnFile/InMemory)
- 2: OK Create at least 3 users, two have the same name but different username
- 3: OK Then, use the mailboxes to send a few emails between them. Make some of them share the
same subject and make enough so that the following tests have results
- 4: OK Get one of the mailboxes and update its mail
- 5: OK List the mailbox messages in the console. (Sorted by newer first.) Use the iterable capabilities
of the mailbox
- 6: OK? Now list the messages by sender username using the mailbox feature
- 7: Filter the messages with the following conditions
	- The message subject contains a certain word
	- The message sender is a certain user
- 8: OK Use the mail system object to retrieve all messages and print them
- 9: Filter messages globally that fulfill the following conditions
	- The message subject is a single word
	- The sender was born after year 2000
- 10: OK Get the count of messages in the system and print it
- 11: revisar Get the average number of messages received per user and print it
- 12: Group the messages per subject in a Map<String, List<Message>> and print it
- 13: revisar Count the words of all messages sent by users with a certain real name
- 14: revisar Use the name that you used on two users. Print the result
- 15: revisar Print the messages received by users born before year 2000
- 16: Now change the mail store to the file implementation
