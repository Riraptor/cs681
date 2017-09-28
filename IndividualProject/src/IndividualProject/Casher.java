package IndividualProject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;


public class Casher{
	private static ReentrantLock slock = new ReentrantLock();
	
	static Casher instance = null;
	private Casher() {
		
	}
	public static Casher getInstance() {
		slock.lock();
		try {
			if (instance == null) {
				instance = new Casher();
			}
		} finally {
			slock.unlock();
		}
		return instance;
	}
	private static Future future = new Future();
	ExecutorService workStealer = Executors.newWorkStealingPool();
	
	public Pizza order(){
		System.out.println("An order is made.");
		
			workStealer.execute(()->{
				RealPizza realPizza = new RealPizza();
				future.setRealPizza( realPizza );
			});
			//workStealer.shutdown();
			return future;
			}

	}


