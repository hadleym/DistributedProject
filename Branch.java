import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


public class Branch implements Runnable {
	Ledger ledger;
	
	public void Branch() {
		ledger = new Ledger(100);
	}	

	public void run() {
		System.out.println("Starting Branch...\n");
		try { 
			ServerSocket listener = new ServerSocket(9090);
			try{
				while(true){
					Socket socket = listener.accept();
					try { 
						Transaction trans = recvTransaction(socket);
						// DO STUFF
						
						sendTransaction(socket);
						System.out.println("Sending response...");
						//out.writeObject(new Date().toString());
					} finally { 
//						socket.close();
					}
				}
			} finally {
				listener.close();
			}
		} catch (IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	public void sendTransaction(Transaction trans, Socket socket) throws IOException  {
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("ATM Sending transaction of type " + trans.getAction());
		out.writeObject(trans);
		System.out.println("ATM, transaction sent.");
	}

	public Transaction recvTransaction(Socket socket) throws IOException, ClassNotFoundException{
		System.out.println("ATM receiving transation...");
		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
		Transaction trans = (Transaction) input.readObject();
		System.out.println("ATM received transaction");
		return trans;
	}
	
}

