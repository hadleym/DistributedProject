class StartBranch{
	public static void main(String[] args){
		Ledger l = new Ledger(100);
		l.addAccount(new Account("John Doe", 1, 100.00));
		l.addAccount(new Account("Billy Madison", 2, 200.00));
		l.addAccount(new Account("Happy Gilmore", 3, 850.00));

		System.out.println("creating branch");
		Branch branch = new Branch(1,l);	
		System.out.println("branch created");
		new Thread(branch).start();
	}
}
