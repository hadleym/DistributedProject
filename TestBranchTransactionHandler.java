import java.util.ArrayList;

class TestBranchTransactionHandler {
	public static void main(String[] args){
		Ledger ledger = new Ledger(10);
		ledger.addAccount(new Account("Micky Mouse", 333, 100.00));
		ledger.addAccount(new Account("Vickie Stralsburg", 87432, 1032.00));
		System.out.println();
		System.out.println();
		System.out.println("TESTING BranchTransactionHandler *****");
		ArrayList<ServerInfo> serverInfoList = new ArrayList<>();

		BranchTransactionHandler handler = new BranchTransactionHandler(ledger, serverInfoList, new ServerInfo("127.0.0.1", 90000));
		System.out.println("Testing withdraw");
		Withdraw w = new Withdraw(333, 50.02);
		Transaction t = handler.handleTransaction(w);	
		System.out.println(t);
		System.out.println("Testing deposit");	
		Deposit d = new Deposit(87432, 1000.01);
		t = handler.handleTransaction(d);
		System.out.println(t);
		System.out.println(	ledger);
		System.out.println();
		System.out.println();
		
	
	}
}
