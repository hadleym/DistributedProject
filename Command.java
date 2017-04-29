class Command{
	Transaction trans;
	int id;
	int password;	
	boolean valid = true;
	public Command(String input){
		String[] split = input.split("\\s+");	
		char c = split[0].charAt(0);
		if (( c == 'b' || c == 'B' ) && split.length == 2){
			try { 
				int id = Integer.parseInt(split[1]);	
				trans = new BalanceInquiry(id);
			} catch ( NumberFormatException e){
				System.out.println(balanceSyntax());
				valid = false;
				trans = null;
			}
		} else if (( c == 'w' || c == 'C' ) && split.length == 3){
			try {
				int id = Integer.parseInt(split[1]);
				double amt = Double.parseDouble(split[2]);
				trans = new Withdraw(id, amt);
			} catch ( NumberFormatException e) {
				System.out.println(withdrawSyntax());
				valid = false;
				trans = null;
			}
		} else if (( c == 'd' || c == 'D' ) && split.length == 3){
			try {
				int id = Integer.parseInt(split[1]);
				double amt = Double.parseDouble(split[2]);
				trans = new Deposit(id, amt);
			} catch ( NumberFormatException e) {
				System.out.println(depositSyntax());
				valid = false;
				trans = null;
			}
		} else {
			printAllSyntax();
			valid = false;
			trans = null;
		}
	}	
	public boolean isValid(){
		return valid;
	}

	public Transaction getTransaction(){
		return trans;
	}

	private void printAllSyntax(){
		System.out.println(balanceSyntax());
		System.out.println(withdrawSyntax());
	}

	private String depositSyntax(){
		return "Deposit format: '[b]alance ID'";
	}
	private String balanceSyntax(){
		return "Balance Inquiry format: '[b]alance ID'";
	}

	private String withdrawSyntax(){
		return "Withdraw format: '[w]ithdraw ID AMT'";
	}
}
