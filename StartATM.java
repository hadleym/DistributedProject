class StartATM{
	public static void main(String[] args){
		(new Thread(new ATM("10.192.144.7"))).start();
	}
}
