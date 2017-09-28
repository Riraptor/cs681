package homework13;

public class Client {
	public static void main(String [] args ) {
	/*
	 * If the lock in Singleton class is removed, the s1 and s2 will have two different HashCodes.
	 * Which will mean that they are different instances.
	 */
		Runnable r1 = () -> {
			Singleton s1 = Singleton.getInstance();
		
			System.out.println(Singleton.getInstance());
		};
		Runnable r2 = () -> {
			Singleton s2 = Singleton.getInstance();
	
			System.out.println(Singleton.getInstance());
		};
		//System.out.println(s1.hashCode());
		//System.out.println(s2.hashCode());
		Thread thread1 = new Thread(r1);
		Thread thread2 = new Thread(r2);
		thread1.start();
		thread2.start();
	}
}
