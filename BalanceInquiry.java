class BalanceInquiry implements Transaction {
	int id;
	Action action;
	double amount;
	public BalanceInquiry(int id){
		this.action = Action.BALANCE_INQUIRY;
		this.id = id;
		this.amount = -1;
	}

	public int getId(){
		return id;
	}

	public Action getAction(){
		return action;
	}

	public Status perform(){
		if ( this.amount != -1){
			return Status.SUCCESS;
		} else {
			return Status.FAILURE;
		}
	}
		
	public void setAmount(double amount){
		this.amount = amount;
	}

	public double getAmount(){
		return amount;
	}
	

}
