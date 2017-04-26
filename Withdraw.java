class Withdraw implements Transaction{
	Account account;
	double amount;
	
	public Withdraw(Account a, double amt){
		account = a;
		amount = amt;
	}
		
	public Action getAction(){
		return Action.WITHDRAW;
	}
	
	public Status perform(){
		if ( account.getBalance() >= amount) {
			account.setBalance(account.getBalance() - amount);
			return Status.SUCCESS;
		} else {
			return Status.FAILURE;
		}
	}
}
