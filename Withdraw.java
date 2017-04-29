import java.io.IOException;
import java.io.Serializable;
class Withdraw implements Transaction, Serializable {
//	Account account;
	double amount;
	Status status;
	int id;
	
	public Withdraw(int i, double amt){
		id = i;
		amount = amt;
		status = Status.FAILURE;
	}
	
	public int getId(){
		return id;
	}

	public double getAmount(){
		return amount;
	}
	
	public void setAmount(double a){
		amount = a;
	}
	@Override
	public Status getStatus(){
		return status;
	}

	@Override
	public void setStatus(Status s){
		status = s;
	}
		
	public Action getAction(){
		return Action.WITHDRAW;
	}
	
	private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
		stream.writeInt(id);
		stream.writeObject((Double) amount);
		stream.writeObject(status);
	}
	
	private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
		id = stream.readInt();
		amount = (Double) stream.readObject();
		status = (Status) stream.readObject();
	}
	
	@Override
	public String toString(){
		return "WITHDRAW \t ID: " + id + "\t AMOUNT: " + amount + "\t STATUS: " + status;
	}
}

