import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ATM implements Runnable {
	ObjectInputStream input = null; 
	ObjectOutputStream out = null; 
	String branchAddress;
	int id;
	public ATM (int i, String addr) {
		id = i;
		branchAddress = addr;
	}
	public void run(){
		try { 
			Scanner s = new Scanner(System.in);	
			Socket socket = new Socket(branchAddress,  9090);
			System.out.println("socket created");
			while(true){	
				System.out.println("Welcome to " + this);
				System.out.println("Enter id: ");
				int userId = Integer.parseInt(s.nextLine());
				Transaction bi = new BalanceInquiry(userId);
				System.out.println("Sending transaction");
				sendTransaction(bi, socket);
				recvTransaction(socket);
			}
			
		} catch (IOException ie){
			ie.printStackTrace();
		}

	}
	
	public void sendTransaction(Transaction trans, Socket socket) throws IOException  {
		if ( out == null ){
			out = new ObjectOutputStream(socket.getOutputStream());
		}
		System.out.println( this + " Sending transaction of type " + trans.getAction());
		out.writeObject(trans);
		System.out.println( this + " transaction sent.");
	}

	public Transaction recvTransaction(Socket socket) throws IOException {
		System.out.println( this + " receiving transation...");
		Transaction trans = null;
		try { 
			if ( input == null ){
				input = new ObjectInputStream(socket.getInputStream());
			}
			trans = (Transaction) input.readObject();
			System.out.println( this + " received transaction");
			if ( trans.getAction() == Action.BALANCE_INQUIRY ) {
				BalanceInquiry bi = (BalanceInquiry) trans;
				System.out.println("Balance: " + bi.getAmount());
			}
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return trans;
	}

	public String toString(){
		return "[[ ATM " + id + " ]] ";
	}
}
