class StartATM{
	String greyhound = "10.192.144.17";
	String bulldog = "10.192.144.7";
	public static void main(String[] args){
		String addr;
		if ( args.length > 0 ){
			addr = args[0];
		} else {
			addr = "10.192.144.7";
		}
		int id = 1;
		(new Thread(new ATM(id, addr))).start();
	}
}
