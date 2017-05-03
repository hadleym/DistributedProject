import java.util.ArrayList;
class ServerList {
	String myIp;
	public ArrayList<String> serverList;
	public ServerList(String ip){
		myIp = ip;
		serverList = new ArrayList<>();
	}


	public void add(String ip){
		serverList.add(ip);
	}
}
		
