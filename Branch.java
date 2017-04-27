import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


public class Branch implements Runnable {
	Ledger ledger;
	
	public Branch() {
		ledger = new Ledger(100);
		System.out.println("Adding account.. in init");
		ledger.addAccount(new Account("John Doe", 1, 100.00));
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
						handleBalanceInquiry((BalanceInquiry)trans);
						sendTransaction(trans, socket);
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
	public void handleBalanceInquiry(BalanceInquiry bi){
		int id = bi.getId();
		double amount = -1;
		Account account = ledger.getAccount(id);
		if ( account != null ){
			amount = account.getBalance();
		}
		bi.setAmount(amount);
	}
			
	public void sendTransaction(Transaction trans, Socket socket) throws IOException  {
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("[Branch] Sending transaction of type " + trans.getAction());
		out.writeObject(trans);
		System.out.println("[Branch] transaction sent.");
	}

	public Transaction recvTransaction(Socket socket) throws IOException, ClassNotFoundException{
		System.out.println("[Branch] receiving transation...");
		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
		Transaction trans = (Transaction) input.readObject();
		System.out.println("[Branch] received transaction");
		return trans;
	}
	
}

