import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Mark Hadley on 4/30/2017.
 */
public class ServerInfo implements Serializable {
    public String address;
    public int port;
    public ServerInfo(String a, int p){
        address = a;
        port = p;

    }
    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        stream.writeInt(port);
        stream.writeObject((String) address);
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        port = stream.readInt();
        address = (String) stream.readObject();
    }
    @Override
    public String toString(){
        return address + ":" + port;
    }
}
