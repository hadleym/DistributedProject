class StartBranch{
	public static void main(String[] args){
		System.out.println("creating branch");
		Branch branch = new Branch();	
		System.out.println("branch created");
		(new Thread(new Branch())).start();
	}
}
