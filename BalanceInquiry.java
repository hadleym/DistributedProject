import java.io.Serializable;
class BalanceInquiry implements Transaction, Serializable {
	private static final long serialVersionUID = 0L;
	int id;
	Action action;
	Status status;
	double amount;
	public BalanceInquiry(int id){
		this.action = Action.BALANCE_INQUIRY;
		this.id = id;
		this.amount = -1;
		this.status = null;
	}

	@Override
	public Status getStatus(){
		return status;
	}
	
	@Override
	public void setStatus(Status s){
		status = s;
	}
	
	public int getId(){
		return id;
	}

	public Action getAction(){
		return action;
	}

	public void setAmount(double amount){
		this.amount = amount;
	}

	public double getAmount(){
		return amount;
	}
	
	@Override
	public String toString(){
		return "BALANCE INQUIRY\t ID: " + id + "\tAMOUNT: " + amount + " STATUS: " + status;
	}	

}
