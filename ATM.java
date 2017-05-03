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
	int port;
	public ATM (int i, String addr, int p) {
		id = i;
		port = p;
		branchAddress = addr;
	}
	public void run(){
		try { 
			Scanner s = new Scanner(System.in);	
			Socket socket = new Socket(branchAddress, port);
			System.out.println("socket created");
			while(true){	
				System.out.println("Welcome to " + this);
				System.out.print("Enter command: ");
				Command c = new Command(s.nextLine());
				while ( !c.isValid()){
					System.out.println("Command not valid...");
					System.out.print("Enter command: ");
					c = new Command(s.nextLine());
				}
				Transaction t =  c.getTransaction();
				System.out.println("Sending transaction "+ t);
				sendTransaction(t, socket);
				t = recvTransaction(socket);
				System.out.println("Response from Branch Received... " + t.getStatus());
				if ( t instanceof CloseTransaction){
				    break;
                }
				System.out.println(t);
			}
			
		} catch (IOException ie){
			ie.printStackTrace();
		}
        System.out.println(this + " is shutting down...");
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
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return trans;
	}

	public String toString(){
		return "[[ ATM " + id + " ]] ";
	}
}
