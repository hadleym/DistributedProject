class BranchTransactionHandler {
	Ledger ledger;
	public BranchTransactionHandler ( Ledger l ){
		ledger = l;	
	}
	public Transaction handleTransaction(Transaction t){
		Account a = ledger.getAccount(t.getId());
		if ( a != null){
			if ( t.getAction() == Action.BALANCE_INQUIRY ){
				return handleBalanceInquiry((BalanceInquiry) t);
			} else if ( t.getAction() == Action.WITHDRAW ){
				return handleWithdraw((Withdraw) t, a);
			} else if ( t.getAction() == Action.DEPOSIT ) {
				return handleDeposit((Deposit) t, a);
			} else {
				System.out.println("This type of transaction cannot be processed by a branch (this error should not appear, please contact administrator.)");
				t.setStatus(Status.FAILURE);
				return t;
			}
		} else { 
			System.out.println("Transaction cannot be processed at this branch." + t.getId());
			t.setStatus(Status.FAILURE);
		}
		return t;
	}

	private Transaction handleDeposit(Deposit d, Account a){
		boolean success = a.deposit(d.getAmount());
		if ( success ) {
			d.setStatus(Status.SUCCESS);
		} else {
			d.setStatus(Status.FAILURE);
		}
		return d;
	}

	private Transaction handleWithdraw(Withdraw w, Account a){
		boolean success = a.withdraw(w.getAmount());
		if ( success ) {
			w.setStatus(Status.SUCCESS);
		} else {
			w.setStatus(Status.FAILURE);
		}
		return w;
	}
		
	private Transaction handleBalanceInquiry(BalanceInquiry bi){
		// verify that the ledger contains the account
		Account a = ledger.getAccount(bi.getId());
		if ( a != null ) {
			bi.setAmount(a.getBalance());
			bi.setStatus(Status.SUCCESS);
		} else {
			bi.setStatus(Status.FAILURE);
		}
		return bi;
	}
}
	
