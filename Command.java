class Command{
	Transaction trans;
	int id;
	int password;	
	boolean valid = true;
	public Command(String input){
		String[] split = input.split("\\s+");	
		char c = split[0].charAt(0);
		int id = Integer.parseInt(split[1]);	
		if ( c == 'b' || c == 'B' ){
			
			trans = new BalanceInquiry(id);
		} else {
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
}
