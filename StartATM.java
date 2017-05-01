class StartATM{
	String greyhound = "10.192.144.17";
	String bulldog = "10.192.144.7";
	public static void main(String[] args){
		String addr;
		int port;
		if ( args.length > 0 ){
			addr = args[0];
			port = Integer.parseInt(args[1]);
		} else {
			System.out.println("Using default addr and port");
			addr = "10.192.144.7";
			port = 9090;
		}
		int id = 1;
		(new Thread(new ATM(id, addr, port))).start();
	}
}
