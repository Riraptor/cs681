package homework19;



public class Casher{
	private static Future future = new Future();

	public Pizza order(){
		System.out.println("An order is made.");
		/*
		new Thread(){
			public void run(){
				RealPizza realPizza = new RealPizza();
				future.setRealPizza( realPizza );
			}
		}.start();
		return future;
	*/
		Thread t = new Thread(()->{
					RealPizza realPizza = new RealPizza();
					future.setRealPizza( realPizza );
							});
		t.start();
		return future;
	}

	public static void main(String[] args) throws InterruptedException{
	
		//ReentrantLock lock = new ReentrantLock();
		Casher casher = new Casher();
		System.out.println("Ordering pizzas at a casher counter.");
		Pizza p1 = casher.order();
		Pizza p2 = casher.order();
		Pizza p3 = casher.order();
		//System.out.println("Doing something, reading newspapers, magazines, etc., " +
			//	"until pizzas are ready to pick up...");
		/*
		while(true){ 
			lock.lock();
			if( future.isReady() ){ 
					future.getPizza(1000);
					break; 
			} 
			lock.unlock(); 
			System.out.println("Doing something");
		}*/
		try{
			Thread.sleep(0);
		}
		catch(InterruptedException e){}
		
		System.out.println("Let's see if pizzas are ready to pick up...");
		System.out.println( p1.getPizza(0) );
		System.out.println( p2.getPizza(0) );
		System.out.println( p3.getPizza(0) );
	}
}
