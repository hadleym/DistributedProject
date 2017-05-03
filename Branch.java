import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Branch {
	public static String addr;
	public static int id;
	public static int port;
    public static ArrayList<ServerInfo> serverInfoList;
    public static ServerInfo serverInfo;
	public static void main(String[] args) throws IOException {
		ArrayList<BranchConnection> connectionList = new ArrayList<BranchConnection>();
		if (args.length < 3){
			System.err.println("Incorrect argument size");
			System.err.println("USAGE: Branch ID IP PORT [remote ip] [remote port]");
			System.exit(1);
		}
		id = Integer.parseInt(args[0]);
		addr =  args[1];
        port = Integer.parseInt(args[2]);
		serverInfo = new ServerInfo(addr, port);
		Ledger ledger = new Ledger(100);
        serverInfoList = new ArrayList<>();

		boolean isDone = false;


		// another ip and port are listed to connect to.
		if ( args.length > 4 ){
		    String remoteIp = args[3];
		    int remotePort = Integer.parseInt(args[4]);
		    ServerInfo otherServer = new ServerInfo(remoteIp, remotePort);
            ServerListRequest request = new ServerListRequest(serverInfoList, serverInfo);
            serverInfoList = SendRequestToOtherServer(request, otherServer);
            System.out.println("List recieved");
            System.out.println("List has size of : " + serverInfoList.size());
            for ( ServerInfo si: serverInfoList){
				System.out.println("Adding this server " + serverInfo + " to " + si + ":");
				addServerToAnother(serverInfo, si);
            }
        }

        BranchTransactionHandler transactionHandler = new BranchTransactionHandler(ledger, serverInfoList, serverInfo);

		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println(branchName() + "...");
		System.out.println("Started Branch on port " + port);
		if ( id == 1 ) {
			ledger.addAccount(new Account("Suzy Greenberg", 2344, 100.0));
			ledger.addAccount(new Account("Corbin Dallas", 12345, 1.0));
		} else if ( id == 2) {
			ledger.addAccount(new Account("Jerry Seinfeld", 5, 100.0));
			ledger.addAccount(new Account("George Costanza", 6, 500.00));
		}
		
	
		while(!isDone){
			
			Socket clientSocket = serverSocket.accept();
			System.out.println("Received connection from client");
		
			BranchConnection branchConnection = new BranchConnection(clientSocket, transactionHandler);
			branchConnection.start();	
			connectionList.add(branchConnection);
		}
		serverSocket.close();
	}
	public static String branchName()	{
		return "[[ Branch" + id + "@" + addr + "]]";
	}

	private static ArrayList<ServerInfo> SendRequestToOtherServer(ServerListRequest r, ServerInfo si){
        System.out.println("Sending request to other server");
        try {
            Socket socket = new Socket(si.address, si.port);
            sendTransaction(r, socket);
            Transaction t = recvTransaction(socket);
            if ( t instanceof ServerListRequest){
                return ((ServerListRequest) t).getList();
            }
        } catch ( IOException e ){
            e.printStackTrace();
            System.out.println("Could not connect to remote branch");
        }

        return r.getList();

    }
	public static void addServerToAnother(ServerInfo source, ServerInfo dest){
		try {
			Socket socket = new Socket(dest.address, dest.port);
			AddBranchTransaction trans = new AddBranchTransaction(source);
			sendTransaction(trans, socket);
			Transaction t = recvTransaction(socket);
		} catch ( IOException e){
			e.printStackTrace();
			System.out.println("Could not connect to remote branch " + dest + " to send new Branch Info");
		}
	}

    public static void sendTransaction(Transaction trans, Socket socket) throws IOException  {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println( branchName() + " Sending transaction of type " + trans.getAction());
        out.writeObject(trans);
        System.out.println( branchName() + " transaction sent.");
    }

    public static Transaction recvTransaction(Socket socket) throws IOException {
        System.out.println( branchName() + " receiving transation...");
        Transaction trans = null;
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            trans = (Transaction) input.readObject();
            System.out.println( branchName() + " received transaction");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return trans;
    }



}

