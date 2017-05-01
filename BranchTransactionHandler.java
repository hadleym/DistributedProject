import com.sun.security.ntlm.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

class BranchTransactionHandler {
	Ledger ledger;
    ArrayList<ServerInfo> serverInfoList;
    ServerInfo myServerInfo;
	public BranchTransactionHandler ( Ledger l, ArrayList<ServerInfo> s, ServerInfo si) {
		myServerInfo = si;
	    ledger = l;
		serverInfoList = s;
	}
	public Transaction handleTransaction(Transaction t){

		Account a = ledger.getAccount(t.getId());
		if ( a != null){
			if ( t.getAction() == Action.BALANCE_INQUIRY ){
				t = handleBalanceInquiry((BalanceInquiry) t);
			} else if ( t.getAction() == Action.WITHDRAW ){
				t = handleWithdraw((Withdraw) t, a);
			} else if ( t.getAction() == Action.DEPOSIT ) {
                t = handleDeposit((Deposit) t, a);
            }
           return t;
   		} else {
            if ( t.getAction() == Action.CLOSE) {
                return handleCloseTransaction((CloseTransaction) t);
            } else if ( t.getAction() == Action.SERVER_REQUEST){
                return handleServerRequest((ServerListRequest) t);

            } else {
                System.out.println("Processing list to see who to contact");

                System.out.println("DEBUG: serverInfoList details");
                for (ServerInfo si : serverInfoList) {

                    System.out.println("DEBUG: contacting -> " + si.address + ":" + si.port);
                    String otherAddr = si.address;
                    int otherPort = si.port;
                    System.out.println("Attempting to contact branch at " + otherAddr + ":" + otherPort);
                    t = handleAnotherServerTransaction(t, otherAddr, otherPort);
                    if (t.getStatus() == Status.SUCCESS) {
                        return t;
                    }
                }
            }
            System.out.println("Transaction cannot be processed at this branch. " + t.getId());
			t.setStatus(Status.FAILURE);
        }
        return t;

	}



	private Transaction handleDeposit(Deposit d, Account a){
		boolean success = a.deposit(d.getAmount());
		if ( success ) {
			d.setStatus(Status.SUCCESS);
		} else {
			d.setStatus(Status.FAILURE);
		}
		return d;
	}

	private Transaction handleWithdraw(Withdraw w, Account a){
		boolean success = a.withdraw(w.getAmount());
		if ( success ) {
			w.setStatus(Status.SUCCESS);
		} else {
			w.setStatus(Status.FAILURE);
		}
		return w;
	}
		
	private Transaction handleBalanceInquiry(BalanceInquiry bi){
		// verify that the ledger contains the account
		Account a = ledger.getAccount(bi.getId());
		if ( a != null ) {
			bi.setAmount(a.getBalance());
			bi.setStatus(Status.SUCCESS);
		} else {
			bi.setStatus(Status.FAILURE);
		}
		return bi;
	}

	private Transaction handleCloseTransaction(CloseTransaction c){
		c.setStatus(Status.SUCCESS);
		return c;
	}


	private Transaction handleAnotherServerTransaction(Transaction t, String addr, int port){
	    try {
            System.out.println("Sending request to another address...");
            System.out.println("IP: " + addr + ":" + port);
	        Socket socket = new Socket(addr, port);
            sendTransaction(t, socket);
            t = recvTransaction(socket);
        } catch( IOException e){
	        System.out.println("Transaction handler couldnt connect to remote branch");
	        e.printStackTrace();
        }
        return t;
    }

    private Transaction handleServerRequest(ServerListRequest r){
	    System.out.println("Sharing server list info to the request");
	    ArrayList<ServerInfo> l = new ArrayList<>();
	    for ( ServerInfo si : serverInfoList){
	        l.add(si);
        }
        l.add(myServerInfo);
	    r.setList(l);
	    r.setStatus(Status.SUCCESS);
        serverInfoList.add(r.sourceServerInfo);
	    return r;
    }

    public void sendTransaction(Transaction trans, Socket socket) throws IOException  {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println( this + " Sending transaction of type " + trans.getAction());
        out.writeObject(trans);
        System.out.println( this + " transaction sent.");
    }
    public Transaction recvTransaction(Socket socket) throws IOException {

        System.out.println( this + " receiving transation...");
        Transaction trans = null;
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            trans = (Transaction) input.readObject();
            System.out.println( this + " received transaction");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return trans;
    }
}
	
