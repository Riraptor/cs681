package homework18;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable{
	private AccessCounter accessCounter = AccessCounter.getInstance();
	private ReentrantLock lock = new ReentrantLock();
	private volatile boolean done = false;
	
    static Path path1 = FileSystems.getDefault().getPath("src","homework18","file_root","a.html");
    static Path path2 = FileSystems.getDefault().getPath("src","homework18","file_root","b.html");
    static Path path3 = FileSystems.getDefault().getPath("src","homework18","file_root","c.txt");
   static Path path;
    public static void filesCreator() {
		String content ="Hello world";
		 try {
			Files.write(path1, content.getBytes());
			Files.write(path2, content.getBytes());
			Files.write(path3, content.getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    /*
     * This method returns a random path.
     */
	public static Path getRandom() {
		Path [] array = new Path[] {path1, path2, path3};
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
	/*
	 * Method to flip the flag to done. done is a volatile variable
	 * Hence it is thread safe.
	 */
	public void setDone() {
		done = true;
	}
	
	@Override
	public void run() {
		/*
		 * the thread that calls run() runs infinitely unless the 'break' is called.
		 */
		while(true) {
			/*
			 * If the setDone is called from the main thread, the current thread terminates.	
			 */
			if(done) {
					System.out.println(Thread.currentThread() + "is done");
					break;
				}
				//lock.lock();
					/*
					 * Picks up one of the files paths at random.
					 */
					path = getRandom();
					accessCounter.increment(path); 
					accessCounter.getCount(path);
					try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							System.out.println(Thread.currentThread() +" is interrupted");
						} 
					// lock.unlock();
			}
	}
	

}
	