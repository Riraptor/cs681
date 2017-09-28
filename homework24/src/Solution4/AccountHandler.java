package Solution4;

import java.util.Random;

public class AccountHandler implements Runnable {
	
	ThreadSafeBankAccount2_Solution4 destination;
	ThreadSafeBankAccount2_Solution4 source;
	double amount;
	public AccountHandler(ThreadSafeBankAccount2_Solution4 source, ThreadSafeBankAccount2_Solution4 destination, double amount) {
		this.destination = destination;
		this.source = source;
		this.amount = amount;
	}
	
	public void transfer(ThreadSafeBankAccount2_Solution4 source, ThreadSafeBankAccount2_Solution4 destination, double amount) throws InterruptedException {	
		Random random = new Random();
		while(true) {
			if( source.getLock().tryLock() ){ 
				try {
					if( destination.getLock().tryLock() ){ 
						try{
							if( source.getBalcnce() < amount) {
								System.out.println("@@@@@@@@@@@@Not Enough funds");
							}
							else {
								source.withdraw(amount);
								destination.deposit(amount);
								System.out.println("!!!!!!!!!!!!!!!!!!!!Funds Transfered");
							}
							return;
						}finally {
							destination.getLock().unlock();
						}
					}
				}finally {
					source.getLock().unlock();
				}
					}
					Thread.sleep(random.nextInt(1000));
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
