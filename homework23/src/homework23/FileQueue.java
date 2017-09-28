package homework23;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FileQueue {

	private ArrayList<File> queue = new ArrayList<File>();
	private int upperLimit;
	private ReentrantLock lock = new ReentrantLock();
	private Condition queueFull= lock.newCondition();
	private Condition queueNotEmpty = lock.newCondition();
	
	public FileQueue(int UpperLimit) {
		this.upperLimit = UpperLimit;
		
		
	}
	
	public void put (File file) throws InterruptedException {
		lock.lock();
		try {
			/*
			 * Waits for queue size to go down, if its packed.
			 */
			if ( queue.size() >= upperLimit) {
					queueFull.await();
			}
			queue.add(file);
			queueNotEmpty.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public File get() throws InterruptedException {
		lock.lock();
		try{
			/*
			 * Waits for the queue to contain some value before removing and indexing.
			 */
			while (queue.size() <=0) {
				queueNotEmpty.await();
			}
			
			return queue.remove(0);
		
		} finally {
			queueFull.signalAll();
			lock.unlock();
		
		}
	}
	
	public void printQueue() {
		for (File f : queue) {
			System.out.println("THE QUEUE: " + f.getName());
		}
	}
}
