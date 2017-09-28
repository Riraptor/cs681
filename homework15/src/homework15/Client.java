package homework15;



public class Client {
	public static void main(String[] args) throws InterruptedException {


	    InterruptiblePrimeNumberGenerator gen2 = new InterruptiblePrimeNumberGenerator(1L, 200L);
		Thread thread2 = new Thread(gen2);
		thread2.start();
		//thread2.join();
		thread2.interrupt();
		gen2.getPrimes().forEach((element)->System.out.println(element));
	    System.out.println("Size = " + gen2.getPrimes().size());
		 
	}
}
