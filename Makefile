TARGET = branch transaction account enums
CC = javac

all: 
	javac *.java

branch: Branch.java transaction
	$(CC) Branch.java

transaction: Transaction.java Withdraw.java BalanceInquiry.java
	$(CC) Transaction.java Withdraw.java BalanceInquiry.java

account: Account.java
	$(CC) Account.java

enums: Action.java Status.java
	$(CC) Status.java Action.java

