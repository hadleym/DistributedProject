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

	public static void main(String[] args) throws IOException {
		ArrayList<BranchConnection> connectionList = new ArrayList<BranchConnection>();
		if (args.length < 3){
			System.err.println("Incorrect argument size");
			System.err.println("USAGE: Branch ID IP PORT");
			System.exit(1);
		}
		id = Integer.parseInt(args[0]);
		addr =  args[1];
		Ledger ledger = new Ledger(100);
		BranchTransactionHandler transactionHandler = new BranchTransactionHandler(ledger);
		boolean isDone = false;
		int port = Integer.parseInt(args[2]);
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


}

