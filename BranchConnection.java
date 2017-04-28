import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class BranchConnection extends Thread{

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean disconnect = false;

	public BranchConnection(Socket s){
		socket = s;
	}
	
	@Override
	public void run(){
		System.out.println("starting connection...");
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			while(!disconnect){
				System.out.println("Reading transaction...");
				Transaction trans = readTransaction();	
				System.out.println("Tranaction read...");
				out.writeObject(handleTransaction(trans));
			}
			disconnect();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	private Transaction handleTransaction(Transaction trans){
		if (trans.getAction() == Action.BALANCE_INQUIRY){
			BalanceInquiry bi = (BalanceInquiry) trans;
			System.out.println("handling balance inquiry");
			bi.setAmount(200.01);
			bi.setStatus(Status.SUCCESS);
		}
		return trans;
	}

	private void disconnect(){
		try {
			in.close();
			out.close();
			socket.close();	
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	private Transaction readTransaction(){
		Transaction trans = null;
		try { 
			trans = (Transaction) in.readObject();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return trans;
	}
}
