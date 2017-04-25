class Account{
	private String name;
	private int id;
	private double balance;

	public Account(String name, int id, double balance){
		this.id = id;
		this.name = name;
		this.balance = balance;
	}		

	public int withdraw(int amount){
		if (amount >= balance){
			balance -= amount;
			return amount;
		} else {
			return -1;
		}
	}
}
