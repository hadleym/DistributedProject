TARGET = branch transaction account enums
CC = javac

all: 
	javac *.java

clean:
	rm -f *.class
