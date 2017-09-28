package IndividualProject;

public class Customer implements Runnable {

	String name;
	
	public Customer(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		double random = Math.random()*10;
		Casher casher = Casher.getInstance();
		System.out.println(this.getCustomer() + " is Ordering pizzas at a casher counter. " + casher.hashCode());
		System.out.println(this.getCustomer() + " will make " + (int)random + " orders");
		Pizza[] pizzaOrders = new Pizza[(int)random];
		
		/*
		 * Different customers have their own run(). So no need to lock
		 */
		for( int i = 0; i <(int)random; i++) {
			pizzaOrders[i] = casher.order();
		}


		try{
			Thread.sleep(1000);
		}
		catch(InterruptedException e){}
		
		System.out.println(this.getCustomer() + " : Let's see if pizzas are ready to pick up...");

		for( int i = 0; i <(int)random; i++) {
			System.out.println(pizzaOrders[i].getPizza(10));
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException{
		Customer CUSTOMER_ONE = new Customer("CUSTOMER_ONE");
		Customer CUSTOMER_TWO = new Customer("CUSTOMER_TWO");
		Customer CUSTOMER_THREE = new Customer("CUSTOMER_THREE");
		
		Thread thread1 = new Thread(CUSTOMER_ONE);
		Thread thread2 = new Thread(CUSTOMER_TWO);
		Thread thread3 = new Thread(CUSTOMER_THREE);
		thread1.start();
		thread2.start();
		thread3.start();
	}
	
	public String getCustomer() {
		return this.name;
	}
}
	

