public interface Transaction {
	public Action getAction();
	public void setStatus(Status s);
	public Status getStatus();
}
		
