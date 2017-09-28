package homework8;

public class Client {
	public static void main(String[] args) throws InterruptedException {
		
		PrimeNumberGenerator gen = new PrimeNumberGenerator(1L, 2000000L);
		Thread thread = new Thread(gen);
		thread.start();
		thread.join();// Calling thread.join() pauses the main thread so that the line below does not execute
		//before the ArrayList prime gets filled by the other thread.
		gen.getPrimes().forEach((element)->System.out.println(element));
		
		//HW 8(continued):
		 PrimeNumberGenerator gen1 = new PrimeNumberGenerator(1L, 1000000L);
		 PrimeNumberGenerator gen2 = new PrimeNumberGenerator(1000001L,2000000L); 
		 Thread t1 = new Thread(gen1); 
		 Thread t2 = new Thread (gen2);
		 t1.start();
		 t2.start(); 
		 t1.join(); 
		 t2.join();
		 gen1.getPrimes().forEach((element)->System.out.println(element));
		 gen2.getPrimes().forEach((element)->System.out.println(element));
	
		
	}
}
