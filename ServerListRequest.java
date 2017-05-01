import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mark Hadley on 4/30/2017.
 */
public class ServerListRequest implements Transaction, Serializable{
    String addr;
    Status status;
    public ServerInfo sourceServerInfo;
    ArrayList<ServerInfo> list;

    public ServerListRequest(ArrayList<ServerInfo> l, ServerInfo s){
        list = l;
        sourceServerInfo = s;
    }

    public void setList(ArrayList<ServerInfo> l ){
        list = l;
    }

    public ArrayList<ServerInfo> getList(){
        return list;
    }



    @Override
    public Action getAction() {
        return Action.SERVER_REQUEST;
    }

    @Override
    public void setStatus(Status s) {
        status = s;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public int getId() {
        return 0;
    }
}
