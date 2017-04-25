class StartBranch{
	public static void main(String[] args){
		Branch branch = new Branch();	
		(new Thread(new Branch())).start();
	}
}
