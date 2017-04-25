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
			System.out.println("Enter IP ADDRESS of machine that is running the date service on port 9090:");
			Socket s = new Socket(serverAddress, 9090);
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String answer = input.readLine();
			System.out.println(answer);
			System.exit(0);
		} catch (IOException ie){
			ie.printStackTrace();
		}
	}
}
