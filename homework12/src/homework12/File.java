package homework12;

import java.util.concurrent.locks.ReentrantLock;

public class File {

	private boolean changed = false;
	ReentrantLock lock = new ReentrantLock();
	
	public void change() {
		try {
			lock.lock();
			System.out.println("File Content Changed");
			changed = true;
		} finally {
			lock.unlock();
		}
	}
	
	public void save() {
		try {
			lock.lock();
			if(changed == true) {
				return;
			}
			if(changed = true) {
				System.out.println("Saved and Stamped");
				changed = false;
			}
		} finally {
			lock.unlock();
		}
	}
}
