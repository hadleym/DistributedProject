import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;
class ATM implements Runnable {
	String serverAddress;
	public ATM (String addr) {
		serverAddress = addr;
	}
	public void run(){
				
		try { 
			System.out.println("Enter command: ");
			Socket s = new Socket(serverAddress, 9090);
			int id = 1;
			Transaction bi = new BalanceInquiry(1);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String answer = input.readLine();
			System.out.println(answer);
			System.exit(0);
		} catch (IOException ie){
			ie.printStackTrace();
		}

	}
	
	public void sendTransaction(Transaction trans, Socket socket){
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		System.out.println("ATM Sending transaction of type " + trans.getAction());
}
