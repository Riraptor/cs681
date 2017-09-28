package homework14_2;

public class Client {
	public static void main(String[] args) {
		System.out.println("Using Atomic Integer");
		Guest Rizan = new Guest();
		Guest Katie = new Guest();
		Guest Cat = new Guest();
		Thread thread1 = new Thread(Rizan);
		Thread thread2 = new Thread(Katie);
		Thread thread3 = new Thread(Cat);
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
