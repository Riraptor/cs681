package homework21;


import java.io.IOException;
import java.nio.file.Path;

//import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable{
	private AccessCounter accessCounter = AccessCounter.getInstance();
	private Path path;
	private FileCache fileCache;
	
	public RequestHandler(Path path, FileCache fileCache) {
		this.path = path;
		this.fileCache = fileCache;
	}
	
	@Override
	public void run() {
		try{
				System.out.println(fileCache.fetch(path));
		}catch(IOException e){
			System.out.println("Fetch Exception" + e);
		}
		accessCounter.increment(path); 
		accessCounter.getCount(path);
		try {
				Thread.sleep(0);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread() +" is interrupted");
		} 
					
	}
	

}
	