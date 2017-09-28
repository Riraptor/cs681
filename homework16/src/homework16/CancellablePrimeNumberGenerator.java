package homework16;

import java.util.concurrent.locks.ReentrantLock;


//tread safe version
public class CancellablePrimeNumberGenerator extends PrimeNumberGenerator{
	private boolean done = false;
	ReentrantLock lock = new ReentrantLock();

	public CancellablePrimeNumberGenerator(long from, long to) {
		super(from, to);
	}
		
	public void setDone(){
		try {
			lock.lock();
		done = true;
		}
		finally{
			lock.unlock();
		}
	}

	public void run(){
		// Stop generating prime numbers if done==true
		for (long n = from; n <= to; n++) {
			try {
				lock.lock();	
				if(done==true){
					System.out.println("Stopped generating prime numbers.");
					this.primes.clear();
					break;
					}
				}finally {
					lock.unlock();
				}
			
				if( isPrime(n) ){ 
					this.primes.add(n); 
				}
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
}
