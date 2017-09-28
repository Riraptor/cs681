package Soultion3a;


public class AccountHandler implements Runnable {
	
	ThreadSafeBankAccount2_Solution3a destination;
	ThreadSafeBankAccount2_Solution3a source;
	double amount;
	public AccountHandler(ThreadSafeBankAccount2_Solution3a source, ThreadSafeBankAccount2_Solution3a destination, double amount) {
		this.destination = destination;
		this.source = source;
		this.amount = amount;
	}
	
	public void transfer(ThreadSafeBankAccount2_Solution3a source, ThreadSafeBankAccount2_Solution3a destination, double amount) {	
		int sourceID = System.identityHashCode(source);
		int destID = System.identityHashCode(destination);
		if( sourceID < destID ){ 
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
		else if (sourceID > destID ){ 
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
