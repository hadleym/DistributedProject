class TestCommands{
	public static void main(String[] args){
		System.out.println("TESTING Commands .... ****** ");
		testCommand(new Command("b 1"), Action.BALANCE_INQUIRY);	
		testCommand(new Command("w 1 10.0"), Action.WITHDRAW);	
		testCommand(new Command("d 1 10.0"), Action.DEPOSIT);	
		/*
		c = new Command("b");
		c = new Command("w 23334 ");
		*/

	}
	
	public static boolean testCommand(Command c, Action a ){
		if ( c.getTransaction().getAction() == a ){
			System.out.println(c.getTransaction() + "\t** PASS **");
			return true;
		} else {
			System.out.println(c.getTransaction() + "\t** FAIL **");
			return false;
		}
	}
		
}
