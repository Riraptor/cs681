package homework27;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Client {
	public static void main(String[] args) throws InterruptedException {

		AccessCounter accessCounter = AccessCounter.getInstance();
		Map<Path,Integer> hash = new HashMap<Path, Integer>();
		int NUM_THREADS = 15;
		RequestHandler[] req= new RequestHandler[NUM_THREADS];
		Thread[] threads = new Thread[NUM_THREADS]; //Array of threads for out Test.
		/*
		 * Initialize all threads and respective runnables for the threads.
		 */
		for (int j = 0; j < NUM_THREADS; j++) {
            req[j] = new RequestHandler();
			threads[j] = new Thread(req[j]);
          
        }
		/*
		 * Start all the threads
		 */
		  for (int i = 0; i < NUM_THREADS; i++) {
	            threads[i].start(); 
	   
	      }
		  /*
		   * Two step thread termination.
		   */
		  for (int i = 0; i < NUM_THREADS; i++) {
			  	threads[i].interrupt();
			  	req[i].setDone();
			  	
		  }
	   
		 hash = accessCounter.getAccessCounter(); 
		 Thread.sleep(1000);
		 System.out.println("Access Counts : ");
		 /*
		  * Printing out the Access counter HashMap.
		  */
		 for( Map.Entry<Path, Integer> entry : hash.entrySet()) {
			 System.out.println("[ " + entry.getKey().getFileName() + ", " + entry.getValue() + " ]");

		 }
		 
	}
}
