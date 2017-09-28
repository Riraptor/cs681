package homework26;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Observable {
	private volatile boolean changed = false;
	ArrayList<Observer> observers;
	private ReentrantLock lock = new ReentrantLock();
	public Observable() {
		observers = new ArrayList<>();
	}
	
	public void addObserver(Observer o) {
		lock.lock();
		try {
			observers.add(o);
		}
		finally {
			lock.unlock();
		}
	}
	
	public void deleteObserver(Observer o) {
		lock.lock();
		try {  
			observers.remove(o);
		} finally {
			lock.unlock();
		}
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
	
	public void notifyObservers(Object arg) {
		lock.lock();
		try {
					
				if( hasChanged()) {
					for (Observer ob: observers) {
						ob.update(this, arg);
					}
					clearChanged();
				} 
		} finally {
			lock.unlock();
		}
	}
	
}
