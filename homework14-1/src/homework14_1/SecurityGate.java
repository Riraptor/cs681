package homework14_1;

import java.util.concurrent.locks.ReentrantLock;

public class SecurityGate {
	private int counter = 0;
	private ReentrantLock lock = new ReentrantLock();
	private static ReentrantLock slock = new ReentrantLock();
	private SecurityGate() {};
	private static SecurityGate instance = null;
	
	public static SecurityGate getInstance() {
		slock.lock();
		try {
			if (instance == null) {
				instance = new SecurityGate();
			}
		} finally {
			slock.unlock();
		}
		return instance;
	}
	public void enter() {
		lock.lock();
		try {
			System.out.println("guest entered");
			counter++;
		} finally {
			lock.unlock();
		}
	}
	
	public void exit() {
		lock.lock();
		try {
			System.out.println("guest existed");
			counter--;
		} finally {
			lock.unlock();
		}
	}
	
	public int getCount() {
		return counter;
	}
}
