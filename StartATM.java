class StartATM{
	public static void main(String[] args){
		(new Thread(new ATM())).start();
	}
}
