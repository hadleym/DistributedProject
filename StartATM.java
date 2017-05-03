class StartATM{
	String greyhound = "10.192.144.17";
	String bulldog = "10.192.144.7";
	public static void main(String[] args){
		String addr;
		int port;
		int id;
		if ( args.length != 3 ){
			System.out.println("USAGE: StartATM id addr port");
			System.out.println("Exiting...");
			System.exit(1);
		} 
		id = Integer.parseInt(args[0]);
		addr = args[1];
		port = Integer.parseInt(args[2]);
	
		(new Thread(new ATM(id, addr, port))).start();
	}
}
