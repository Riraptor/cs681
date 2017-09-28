package homework22;

import java.io.IOException;
import java.nio.file.FileSystems;

import java.nio.file.Path;

public class Client {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
	
		LFUFileCacheRW LFU = new LFUFileCacheRW(); //Singleton instance
		//If we make another FileCache object, it will also be the same instance as previous. 
		//They will have the same hashCode() as well because its the same instance.
		Path CachePathA = FileSystems.getDefault().getPath("src","homework22","file_root","a.html");
		Path CachePathB = FileSystems.getDefault().getPath("src","homework22","file_root","b.txt");
		Path CachePathC = FileSystems.getDefault().getPath("src","homework22","file_root","c.txt");
		Path CachePathD = FileSystems.getDefault().getPath("src","homework22","file_root","d.txt");
		Path CachePathE = FileSystems.getDefault().getPath("src","homework22","file_root","e.txt");
		Path CachePathF = FileSystems.getDefault().getPath("src","homework22","file_root","f.txt");
		
		
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
		
		
		
		
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		thread9.start();
		thread10.start();
		thread11.start();
		thread12.start();
		
		
		


	}
}

