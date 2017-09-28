package Solution3;

public class AccountHandler implements Runnable {
	
	ThreadSafeBankAccount2_Solution3 destination;
	ThreadSafeBankAccount2_Solution3 source;
	double amount;
	public AccountHandler(ThreadSafeBankAccount2_Solution3 source, ThreadSafeBankAccount2_Solution3 destination, double amount) {
		this.destination = destination;
		this.source = source;
		this.amount = amount;
	}
	
	public void transfer(ThreadSafeBankAccount2_Solution3 source, ThreadSafeBankAccount2_Solution3 destination, double amount) {	

		if( source.getAcctNum() < destination.getAcctNum() ){ 
			source.getLock().lock(); 
			destination.getLock().lock();
		
							if( source.getBalcnce() < amount) {
								System.out.println("@@@@@@@@@@@@Not Enough funds");
							}
							else {
								source.withdraw(amount);
								destination.deposit(amount);
								System.out.println("!!!!!!!!!!!!!!!!!!!!Funds Transfered");
							}
			destination.getLock().unlock(); 
			source.getLock().unlock();
		}
		else if( source.getAcctNum() > destination.getAcctNum() ){ 
					destination.getLock().lock(); 
					source.getLock().lock();
							if( destination.getBalcnce() < amount) {
								System.out.println("@@@@@@@@@@@@Not Enough funds");
							}
							else {
								destination.withdraw(amount);
								source.deposit(amount);
								System.out.println("!!!!!!!!!!!!!!!!!!!!Funds Transfered");
							}
					source.getLock().unlock(); 
					destination.getLock().unlock(); 
				}

		
	}
	
	@Override
	public void run(){
		try{
			for(int i = 0; i < 10; i++) {
				transfer(source,destination, amount);
				Thread.sleep(2);
			}
		}
		catch(InterruptedException exception){}
	}

}
