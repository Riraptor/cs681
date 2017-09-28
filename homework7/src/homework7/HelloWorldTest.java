package homework7;

public class HelloWorldTest {
	public static void main(String[] args) {
		GreetingRunnable runnable1 = new GreetingRunnable("HelloWorld");
		Thread thread1 = new Thread(runnable1);
		thread1.start();
		
	}
}
