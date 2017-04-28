class Command{
	Transaction trans;
	int id;
	int password;	
	public Command(String input){
		
		String[] split = input.split("\\s+");	
		char c = split[0].charAt(0);
		int id = Integer.parseInt(split[1]);	
		if ( c == 'b' || c == 'B' ){
			trans = new BalanceInquiry(id);
		} else {
			trans = null;
		}
	}	

	public Transaction getTransaction(){
		return trans;
	}
}
