all : build run

build : src/Main.java
	javac -Xlint:unchecked -d classes/ src/Main.java

run :	classes/Main.class
	java -cp classes Main

clean : classes/Main.class
	rm classes/Main.class