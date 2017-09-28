package homework28;


public class Client {
	public static void main(String[] arges) throws InterruptedException {
		 Observable observable = new Observable();
		// observable.setChanged(); 
		// observable.addObserver( (Observable o, Object obj)-> {System.out.println(obj);} );
		 //observable.notifyObservers("Hello Wrld!");
	
		 Thread thread1 = new Thread(() -> {
			 				observable.addObserver((Observable o, Object obj)-> {
			 														System.out.println("Thread1 " + obj);
			 														});
							 });
		 
		 Thread thread2 = new Thread(() -> {
				observable.addObserver((Observable o, Object obj)-> {
														System.out.println("Thread2 " + obj);
														});
				 });	
		 
		 Thread thread3 = new Thread(() -> {
				observable.addObserver((Observable o, Object obj)-> {
														System.out.println("Thread3 " + obj);
														});
				 });
		 thread1.start();
		 thread2.start();
		 thread3.start();
		 thread1.join();
		 thread2.join();
		 thread3.join();
		observable.setChanged();
		System.out.println("USING CopyOnWriteArrayList : ");
		observable.notifyObservers("Hello World!");
	}
}
