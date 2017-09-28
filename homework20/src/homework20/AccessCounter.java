package homework20;


import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * Using ReentrantReadWrite Lock instead  of regular ReentantLock.
 */
public class AccessCounter {
	private HashMap<Path, Integer> accessCounter = new HashMap<Path, Integer>();
	private AccessCounter() {};
	private static AccessCounter instance = null;
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private static ReentrantLock slock = new ReentrantLock();
	
	public static AccessCounter getInstance() {
		slock.lock();
		try {
			if (instance == null) {
				instance = new AccessCounter();
			}
		} finally {
			slock.unlock();
		}
		return instance;
	}
	
	public void increment(Path pathName) {
		lock.writeLock().lock();
		try {
			//System.out.println(" Increment");
			if (accessCounter.get(pathName) != null) {
				accessCounter.put( pathName, getCount(pathName) + 1);
			}
			else {
				accessCounter.put(pathName, 1);
			}
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	public int getCount(Path pathName) {
		lock.readLock().lock();
		try {
			
			if (accessCounter.get(pathName) != null) {
				
				return accessCounter.get(pathName);
			}
			else {
				return 0;
			}
		} finally {
			lock.readLock().unlock();
		}
	}
	
	public HashMap<Path, Integer> getAccessCounter() {
	
		return accessCounter;
		
	}
	
}
