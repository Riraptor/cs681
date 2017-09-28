package homework17;


//tread safe version with Volatile variable.
public class CancellablePrimeNumberGenerator extends PrimeNumberGenerator{
	private volatile boolean done = false;

	public CancellablePrimeNumberGenerator(long from, long to) {
		super(from, to);
	}
		
	public void setDone(){
	
		done = true;
		
	}

	public void run(){
		// Stop generating prime numbers if done==true
		for (long n = from; n <= to; n++) {
		
				if(done){
					System.out.println("Stopped generating prime numbers.");
					this.primes.clear();
					break;
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
