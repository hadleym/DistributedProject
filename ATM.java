import java.io.ObjectInputStream;
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
			System.out.println("Enter IP ADDRESS of machine that is running the date service on port 9090:");
			Socket s = new Socket("127.0.0.1", 9090);
			ObjectInputStream input = new ObjectInputStream(s.getInputStream());
			String answer = (String) input.readObject();
			System.out.println(answer);
			System.exit(0);
		} catch (IOException ie){
			ie.printStackTrace();
		} catch (ClassNotFoundException c){
			c.printStackTrace();
		}

	}
	
	public void sendTransaction(Transaction trans, Socket socket){
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		System.out.println("ATM Sending transaction of type " + trans.getAction());
}
