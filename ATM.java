import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ATM implements Runnable {
	String serverAddress;
	public ATM () {
	}
	public void run(){
				
		try { 

			Socket socket = new Socket("127.0.0.1", 9090);
			System.out.println("socket created");
//			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			Transaction bi = new BalanceInquiry(1);
			System.out.println("Sending transaction");
			sendTransaction(bi, socket);
			recvTransaction(socket);
			System.exit(0);
		} catch (IOException ie){
			ie.printStackTrace();
		}

	}
	
	public void sendTransaction(Transaction trans, Socket socket) throws IOException  {
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("ATM Sending transaction of type " + trans.getAction());
		out.writeObject(trans);
		System.out.println("ATM, transaction sent.");
	}

	public Transaction recvTransaction(Socket socket) throws IOException {
		System.out.println("ATM receiving transation...");
		Transaction trans = null;
		try { 
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			trans = (Transaction) input.readObject();
			System.out.println("ATM received transaction");
			if ( trans.getAction() == Action.BALANCE_INQUIRY ) {
				BalanceInquiry bi = (BalanceInquiry) trans;
				System.out.println("Balance: " + bi.getAmount());
			}
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return trans;
	}

}
