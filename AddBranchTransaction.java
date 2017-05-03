import java.io.Serializable;
class AddBranchTransaction implements Transaction, Serializable {
	ServerInfo serverInfo;
	Status status;	
	
	public AddBranchTransaction(ServerInfo si){
		serverInfo = si;
	}

	public Action getAction(){
		return Action.SERVER_ADD;
	}

	public int getId(){
		return 0;
	}

	public ServerInfo getServerInfo(){
		return serverInfo;
	}
	public void setStatus(Status s){
		status = s;
	}
	
	public Status getStatus(){
		return status;
	}
}
	
