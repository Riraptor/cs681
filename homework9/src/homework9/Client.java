package homework9;


public class Client {
	public static void main(String[] args) throws InterruptedException {
	System.out.println("For CancellablePrimeNumberGenerator:");
		CancellablePrimeNumberGenerator gen1 = new CancellablePrimeNumberGenerator(1L, 200L);
		Thread thread1 = new Thread(gen1);
		thread1.start();
		//thread.join();
		gen1.setDone();
		gen1.getPrimes().forEach((element)->System.out.println(element));
	    System.out.println("Size = " + gen1.getPrimes().size());
		 
	    System.out.println("\nFor InterruptiblePrimeNumberGenerator:");
	    InterruptiblePrimeNumberGenerator gen2 = new InterruptiblePrimeNumberGenerator(1L, 200L);
		Thread thread2 = new Thread(gen2);
		thread2.start();
		//thread.join();
		thread2.interrupt();
		gen2.getPrimes().forEach((element)->System.out.println(element));
	    System.out.println("Size = " + gen2.getPrimes().size());
		 
	}
}
