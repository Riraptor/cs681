package homework21;

import java.io.IOException;
import java.nio.file.FileSystems;

import java.nio.file.Path;


public class Client {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
	
		LFUFileCache LFU = new LFUFileCache(); //Singleton instance
		//FIFOFileCache FIFO = new FIFOFileCache();
		//If we make another FileCache object, it will also be the same instance as previous. 
		//They will have the same hashCode() as well because its the same instance.
		Path CachePathA = FileSystems.getDefault().getPath("src","homework21","file_root","a.html");
		Path CachePathB = FileSystems.getDefault().getPath("src","homework21","file_root","b.txt");
		Path CachePathC = FileSystems.getDefault().getPath("src","homework21","file_root","c.txt");
		Path CachePathD = FileSystems.getDefault().getPath("src","homework21","file_root","d.txt");
		Path CachePathE = FileSystems.getDefault().getPath("src","homework21","file_root","e.txt");
		Path CachePathF = FileSystems.getDefault().getPath("src","homework21","file_root","f.txt");
		
		
		RequestHandler req1 = new RequestHandler(CachePathA, LFU); 
		RequestHandler req2 = new RequestHandler(CachePathB, LFU);
		RequestHandler req3 = new RequestHandler(CachePathC, LFU);
		RequestHandler req4 = new RequestHandler(CachePathD, LFU);
		RequestHandler req5 = new RequestHandler(CachePathE, LFU);
		RequestHandler req6 = new RequestHandler(CachePathF, LFU);
		RequestHandler req7 = new RequestHandler(CachePathA, LFU);
		RequestHandler req8 = new RequestHandler(CachePathB, LFU);
		RequestHandler req9 = new RequestHandler(CachePathC, LFU);
		RequestHandler req10 = new RequestHandler(CachePathD, LFU);
		RequestHandler req11 = new RequestHandler(CachePathE, LFU);
		RequestHandler req12 = new RequestHandler(CachePathF, LFU);
		
		//RequestHandler req13 = new RequestHandler(CachePathA, FIFO);
		
		
		
		Thread thread1 = new Thread(req1);
		Thread thread2 = new Thread(req2);
		Thread thread3 = new Thread(req3);
		Thread thread4 = new Thread(req4);
		Thread thread5 = new Thread(req5);
		Thread thread6 = new Thread(req6);
		Thread thread7 = new Thread(req7);
		Thread thread8 = new Thread(req8);
		Thread thread9 = new Thread(req9);
		Thread thread10 = new Thread(req10);
		Thread thread11 = new Thread(req11);
		Thread thread12 = new Thread(req12);
		//Thread thread15 = new Thread(req13);	
		
		
		
		
		
		thread1.start();//Thread.sleep(1000);
		thread2.start();//Thread.sleep(1000);
		thread3.start();//Thread.sleep(1000);//Thread.sleep(2000);
		thread4.start();//Thread.sleep(1000);//Thread.sleep(2000);
		thread5.start();//Thread.sleep(1000);//Thread.sleep(2000);
		thread6.start();//Thread.sleep(1000);
		thread7.start();//Thread.sleep(1000);
		thread8.start();//Thread.sleep(1000);
		thread9.start();//Thread.sleep(1000);
		thread10.start();//Thread.sleep(1000);
		thread11.start();//Thread.sleep(1000);
		thread12.start();//Thread.sleep(1000);
		//thread15.start();
		
		
		


	}
}

