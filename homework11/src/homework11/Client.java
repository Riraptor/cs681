package homework11;



public class Client {
	public static void main(String[] args) throws InterruptedException {
	System.out.println("USING try- catch AND BALKING :");
		System.out.println("For CancellablePrimeNumberGenerator:");
		CancellablePrimeNumberGenerator gen = new CancellablePrimeNumberGenerator(1L, 200L);
		Thread t = new Thread(gen);
		t.start();
		//t.interrupt();
		gen.setDone();
		gen.getPrimes().forEach((element)->System.out.println(element));
	    System.out.println("Size = " + gen.getPrimes().size());

		 
	}
}