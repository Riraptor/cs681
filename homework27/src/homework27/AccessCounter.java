package homework27;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	private Map<Path, Integer> accessCounter = new ConcurrentHashMap<Path, Integer>();
	private AccessCounter() {};
	private static AccessCounter instance = null;
	//private ReentrantLock lock = new ReentrantLock();
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
		//lock.lock();
		try {
			//System.out.println(" Increment");
			if (accessCounter.get(pathName) != null) {
				accessCounter.put( pathName, getCount(pathName) + 1);
			}
			else {
				accessCounter.put(pathName, 1);
			}
		} finally {
			//lock.unlock();
		}
	}
	
	public int getCount(Path pathName) {
		//lock.lock();
		try {
			
			if (accessCounter.get(pathName) != null) {
				//System.out.println(" Thread count : " + accessCounter.get(pathName) );
				return accessCounter.get(pathName);
			}
			else {
				return 0;
			}
		} finally {
			//lock.unlock();
		}
	}
	
	public Map<Path, Integer> getAccessCounter() {
	
		return accessCounter;
		
	}
	
}
