import java.util.HashMap;

class Ledger { 
	int maxAccounts;
	HashMap<Integer, Account> map;
	public Ledger (int max){
		maxAccounts = max;
		map = new HashMap<Integer, Account>();
	}

	public void addAccount(Account n){
		map.put(n.getId, n);
	}
}
	
