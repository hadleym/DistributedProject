class Account{
	private String name;
	private int id;
	private double balance;

	public Account(String name, int id, double balance){
		this.id = id;
		this.name = name;
		this.balance = balance;
	}		

	public String getName(){
		return name;
	}

	public void setName(String n){
		name = name;
	}
	
	public void setId(int i){
		id = i;
	}
	
	public int getId(){
		return id;
	}
	
	public void setBalance(double a){
		balance = a;
	}

	public double getBalance(){
		return balance;
	}
}
