class StartATM{
	public static void main(String[] args){
		String addr = "127.0.0.1";
		int id = 1;
		(new Thread(new ATM(id, addr))).start();
	}
}
