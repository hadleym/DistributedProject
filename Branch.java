import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.io.ObjectOutputStream;


public class Branch implements Runnable {
	private HashMap<Integer, Account> ledger;
	
	public void Branch() {
		initLedger();
	}	

	public void run() {
		System.out.println("Starting Branch...\n");
		try { 
			ServerSocket listener = new ServerSocket(9090);
			try{
				while(true){
					Socket socket = listener.accept();
					try { 
						ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//						PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
						System.out.println("Sending response...");
						out.writeObject(new Date().toString());
					} finally { 
						socket.close();
					}
				}
			} finally {
				listener.close();
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	private void	 initLedger(){
		ledger = new HashMap<>();
	}	
}

