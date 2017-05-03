import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class BranchConnection extends Thread{

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean disconnect = false;
	private Branch branch;
	private BranchTransactionHandler transactionHandler;

	public BranchConnection(Socket s, BranchTransactionHandler b){
		transactionHandler = b;
		socket = s;
	}
	
	@Override
	public void run(){
		System.out.println("starting connection...");
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			while(!disconnect){
				System.out.print("Reading transaction...");
				Transaction trans = readTransaction();	
				trans = transactionHandler.handleTransaction(trans);
				System.out.println("Tranaction read...");
				out.writeObject((trans));
				if (trans instanceof CloseTransaction || trans instanceof ServerListRequest){
					break;
				}
			}
			disconnect();
		} catch (IOException e){
			e.printStackTrace();
		}
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
			System.out.println("Transaction of type " + trans.getAction() + " received.");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return trans;
	}
}
