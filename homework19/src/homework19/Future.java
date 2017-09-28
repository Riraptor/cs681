package homework19;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Future implements Pizza{
	private RealPizza realPizza = null;
	private ReentrantLock lock;
	private Condition ready;
	
	public Future(){
		lock = new ReentrantLock();
		ready = lock.newCondition();
	}
	
	public void setRealPizza( RealPizza real ){
		lock.lock();
		try{
			if( realPizza != null ){ return; }
			realPizza = real;
			ready.signalAll();
		}
		finally{
			lock.unlock();
		}
	}

	public String getPizza(long timeout){
		String pizzaData = null;
		
		lock.lock();
		try{
			if( realPizza == null ){
				ready.await(timeout, TimeUnit.MILLISECONDS);	
			}
			
			pizzaData = realPizza.getPizza(0);
		}
		catch(InterruptedException e){
			System.out.println("Interrupted");
		}
		catch(NullPointerException e) {
			System.out.println("TIMEOUT EXCEPTION");
		}
		finally{
			lock.unlock();
		}
		return pizzaData;
	}
	public boolean isReady()  {
			if(realPizza == null) {return false;}
			else return true;
	}
}
