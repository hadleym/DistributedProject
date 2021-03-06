To compile: 
$ make all

USAGE of Branch.java:
$ java Branch ID ADDR PORT [ADDR] [PORT]

USAGE of StartATM.java:
$ java StartATM ID ADDR PORT

ADDR: Address of the branch.
PORT: port of the branch.

If you use the ID value of "1" , "2", or "3", then the Branch.java program will self populate with different Accounts.

EXAMPLE:
user@10.197.144.7 $ java Branch 1 10.197.144.7 9090
user@10.197.144.8 $ java StartATM 1 10.197.144.7 9090
user@10.197.144.9 $ java Branch 2 10.197.144.9 8888 10.197.144.7 9090
user@10.197.144.10 $ java StartATM 42 10.197.144.9 8888 

OR EXAMPLE THAT WORKS ON GS 227 MACHINES:
user@pug $ java Branch 1 pug 9090
user@greyhound $ java StartATM 1 pug 9090
user@bulldog $ java Branch 2 bulldog 8888 pug 9090
user@elkhound $ java StartATM 2 bulldog 8888

The idea is to start a Branch(server) via the Branch.java main method (Step 1 below) on a machine with give ADDR and PORT as arguments.
Any number of ATMS can also be on other machines, and will connect to any Branch that you give its ADDR and PORT for (Step 2).
Additional Branches can run on other machines, or same machine, as long as they have a different PORT. 
Whenever you create an additional Branch (Step 3), you should give at least one ADDR and PORT combination of another Branch that is already running.  These optional arguments will allow the branches to share each other's ADDR and PORT information, so that ATMs for a branch can share information and requests with other Branches, creating a network of Branches.


1) Start a Branch on a machine.
	- $ java Branch ID1 ADDR1 PORT1

2) Start an ATM for that Branch on another machine
	- $ java StartATM ID ADDR1 PORT1

3) Start another Branch on another machine
	- $ java Branch ID2 ADDR2 PORT2 ADDR1 PORT1

4) Start an ATM for the second Branch on another machine
	- $ java StartATM ID ADDR2 PORT2

5) You can start a 3rd Branch, and give it either ADDR1 PORT1 *OR* ADDR2 PORT2 as additional arguments, and it will have the same effect of creating a network of 3 Branches.

ADDR1: Address of machine 1
PORT1: port of machine 1
ADDRN: address of machine N
PORTN: port of Machine N
** ID: id's are simply for display purposes and have no bearing on communication between processes. **

Commands for the ATM processes:
 "balance id"   -- returns the balance of the account with id
 "withdraw id amt" -- preforms a withdraw on the account with id.
 "deposit id amt" -- performs a deposit on the account with id.
 "close" -- closes the atm and disconnects from the branch.

The ATM.java process will also prompt you for a correct command, and tell you if a command is invalid.
