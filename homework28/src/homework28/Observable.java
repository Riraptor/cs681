package homework28;


import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
/*
 * Using CopyOnWriteArrayList.
 * Use this when the number of read threads is a lot higher than the write threads.
 */
public class Observable {
	private volatile boolean changed = false;
	CopyOnWriteArrayList<Observer> observers;
	private ReentrantLock lock = new ReentrantLock();
	public Observable() {
		observers = new CopyOnWriteArrayList<>();
	}
	
	public void addObserver(Observer o) {
		
			observers.add(o);
	}
	
	public void deleteObserver(Observer o) {
		 
			observers.remove(o);
	}
	
	protected void setChanged() {
		changed = true;
	}
	
	protected void clearChanged() {
		changed = false;
	}
	
	public boolean hasChanged() {
		return changed;
	}
	
	void notifyObservers(Object arg){ 
			lock.lock(); 
			try {
				if(!changed) return;
				changed = false;
			} finally {
				lock.unlock(); 
			}
			Iterator<Observer> it = observers.iterator();
			while( it.hasNext() ){ 
				((Observer) it.next()).update(this, arg);
			} 
		}
	
	
}
