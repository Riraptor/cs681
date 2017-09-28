package homework12;

import java.util.concurrent.locks.ReentrantLock;

public class Editor implements Runnable{
	ReentrantLock lock = new ReentrantLock();
	ReentrantLock lock2 = new ReentrantLock();
	private boolean done = false;
	private File aFile = new File();;
	@Override
	public void run(){
		while(true) {
			lock.lock();
			try {
				if (done == true) {
					System.out.println("EDITOR THREAD DONE!");
			
				break;
				}
		   aFile.change();
				aFile.save();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} finally {
				lock.unlock();
			}
		}
		
	}
	
	
	public void setDone() {
		try {
			lock2.lock();
		done = true;
		}
		finally{
			lock2.unlock();
		}
	}

}
