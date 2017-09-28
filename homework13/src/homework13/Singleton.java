package homework13;

import java.util.concurrent.locks.ReentrantLock;

public class Singleton{ 
	private static ReentrantLock lock = new ReentrantLock();
	private Singleton(){
	
	};
	private static Singleton instance = null;
	
	// Factory method to return a singleton instance 
	public static Singleton getInstance() {
		lock.lock();	
		try {
				if(instance == null) {
					instance = new Singleton();
				}
				
			} finally {
				lock.unlock();
			}
		return instance; 
		} 
}

