import java.io.IOException;
class Withdraw implements Transaction{
	Account account;
	double amount;
	Status status;
	
	public Withdraw(Account a, double amt){
		account = a;
		amount = amt;
		status = null;
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
		stream.writeObject(account);
		stream.writeDouble(amount);
		stream.writeObject(status);
	}
	
	private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
		account = (Account) stream.readObject();
		amount = (double) stream.readObject();
		status = (Status) stream.readObject();
	}
}
