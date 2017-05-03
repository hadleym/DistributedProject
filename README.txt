to compile: 
$ make all

1) Start a Branch on a machine.
	- $ java Branch ID1 ADDR1 PORT1

2) Start an ATM for that Branch on another machine
	- $ java StartATM ID ADDR1 PORT1

3) Start another Branch on another machine
	- $ java Branch ID2 ADDR2 PORT2 ADDR1 PORT1

4) Start an ATM for the second Branch on another machine
	- $ java StartATM ID ADDR2 PORT2

Commands for the ATM processes:
 balance id   -- returns the balance of the account with id
 withdraw id amt -- preforms a withdraw on the account with id.
 deposit id amt -- performs a deposit on the account with id.
 close -- closes the atm and disconnects from the branch.

