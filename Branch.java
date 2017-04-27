import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


public class Branch implements Runnable {
	ObjectOutputStream out = null;
	ObjectInputStream input = null;
	Socket socket = null;	
	Ledger ledger;
	int id;
	
	public Branch(int id, Ledger l) {
		this.id = id;
		ledger = l;
	}	

	public void run() {
		System.out.println(this + "starting...");
		try { 
			ServerSocket listener = new ServerSocket(9090);
			try{
				while(true){
					if ( socket == null){
						socket = listener.accept();
					}
					try { 
						Transaction trans = recvTransaction(socket);
						// DO STUFF
						handleBalanceInquiry((BalanceInquiry)trans);
						sendTransaction(trans, socket);
						System.out.println(this + "Sending response...");
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
		System.out.println(this + " for customer id: + " + id );
	}
			
	public void sendTransaction(Transaction trans, Socket socket) throws IOException  {
		if ( out == null ){
			out = new ObjectOutputStream(socket.getOutputStream());
		}
		System.out.println(this + " Sending transaction of type " + trans.getAction());
		out.writeObject(trans);
		System.out.println(this + " transaction sent.");
	}

	public Transaction recvTransaction(Socket socket) throws IOException, ClassNotFoundException{
		System.out.println( this + " receiving transation...");
		if (input == null){
			input = new ObjectInputStream(socket.getInputStream());
		}
		Transaction trans = (Transaction) input.readObject();
		System.out.println(this + " received transaction");
		return trans;
	}

	public String toString(){
		return "[[ Branch " + id + " ]] ";
	}
	
}

