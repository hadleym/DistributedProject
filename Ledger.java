import java.util.HashMap;

class Ledger { 
	int maxAccounts;
	HashMap<Integer, Account> map;
	public Ledger (int max){
		System.out.println("Creating ledger");
		maxAccounts = max;
		map = new HashMap<Integer, Account>();
	}

	public Account getAccount(int id){
		if ( map.containsKey(id)) {
			return map.get(id);
		} else {
			return null;
		}
	}
	public void addAccount(Account n){
		System.out.println("Adding account\tid: " + n.getId() + "\tName: " + n.getName() + "\tAmount: $" + n.getBalance());
		map.put(n.getId(), n);
	}

	@Override
	public String toString(){
		String s = new String();
		for ( int i : map.keySet()){
			s += map.get(i) + "\n";
		}
		return s;
	}
			
}
	
