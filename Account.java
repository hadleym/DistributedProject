class Account{
	private String name;
	private int id;
	private double balance;

	public Account(String name, int id, double balance){
		this.id = id;
		this.name = name;
		this.balance = balance;
	}		

	public synchronized String getName(){
		return name;
	}

	public synchronized void setName(String n){
		name = name;
	}
	
	public synchronized void setId(int i){
		id = i;
	}
	
	public synchronized int getId(){
		return id;
	}
	
	public synchronized void setBalance(double a){
		balance = a;
	}

	public synchronized double getBalance(){
		return balance;
	}

	public synchronized boolean withdraw(double amt){
		if ( balance >= amt ){
			balance -=amt;
			return true;
		} else {
			return false;
		}
	}

	public synchronized boolean deposit(double amt){
		balance += amt;
		return true;
	}
	
	@Override
	public String toString(){
		return "NAME: " + name + "\t ID: " + id + "\t BALANCE: " + balance;
	}
}
