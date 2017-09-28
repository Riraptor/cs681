package homework4;

import java.util.ArrayList;

public class Observable {
	boolean changed;
	ArrayList<Observer> observers;
	
	public Observable() {
		observers = new ArrayList<>();
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
	
	public void notifyObservers(Object arg) {
		if( hasChanged()) {
			for (Observer ob: observers) {
				ob.update(this, arg);
			}
			clearChanged();
		}
	}
	
}
