import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Branch {
	public static void main(String[] args) throws IOException {
		ArrayList<BranchConnection> connectionList = new ArrayList<BranchConnection>();
		if (args.length < 1){
			System.err.println("Incorrect argument size");
			System.err.println("USAGE: Branch ID");
			System.exit(1);
		}
		boolean isDone = false;
		int port = 9090;
		ServerSocket serverSocket = new ServerSocket(port);

		System.out.println("Started Branch on port " + port);
		
		while(!isDone){
			
			Socket clientSocket = serverSocket.accept();
			System.out.println("Received connection from client");
		
			BranchConnection branchConnection = new BranchConnection(clientSocket);
			branchConnection.start();	
			connectionList.add(branchConnection);
		}
		serverSocket.close();
	}
}

