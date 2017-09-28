package homework16;



public class Client {
	public static void main(String[] args) throws InterruptedException {
	System.out.println("CancellablePrimeNumberGenerator with two-step thread termination:");
		CancellablePrimeNumberGenerator gen = new CancellablePrimeNumberGenerator(1L, 200L);
		Thread t = new Thread(gen);
		t.start();	
		//t.join();
		t.interrupt();
	
		gen.setDone();
		gen.getPrimes().forEach((element)->System.out.println(element));
	    System.out.println("Size = " + gen.getPrimes().size());

		 
	}
}