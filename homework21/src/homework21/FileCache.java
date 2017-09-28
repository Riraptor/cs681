package homework21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;



public abstract class FileCache {
	ReentrantLock cacheLock = new ReentrantLock();
	public int max_cache_size = 3;
	public String targetFile;
	protected LinkedHashMap<Path, String> cache = new LinkedHashMap<Path, String>();
	protected HashMap<Path, Date> timeMap = new HashMap<Path, Date>();
	protected AccessCounter accessCounter = AccessCounter.getInstance();
	
	public String CacheString = "src/homework21/CacheFolder/";
	public String DiskPathstring = "src/homework21/DiskFiles/";
	//public Path HashPath = FileSystems.getDefault().getPath(CacheString,"HashStore.ser");
	static FileCache instance = null;
	
	/*
	 * 	Private because Singleton class. Other classes cannot create new FileCache.
	 */
	public FileCache() {	
	

	}

	public String fetch(Path targetFile) throws IOException {
		Date date = new Date();
		cacheLock.lock();
		
		try{
			/*
			 * If the file is already Cashed : 
			 */
				timeMap.put(targetFile, date);
				if (cache.get(targetFile) != null) {			
					return " Nothing Added to hashmap from Cache";
			}
			/*
			 * If the file is not cashed yet:
			 */
			else {					
					/*
					 *  If cache size is not full
				     */
					if (cache.size() < max_cache_size) {		
						cacheFile(targetFile);
					 return "Done";
					}
					/*
					 *  If cache size is full
					 */
					else{
						replace(targetFile);
						return "File Replaced";
					} 
			
			}
		} catch (Exception e) {
				return "Things went wrong: " + e.getMessage();
		} finally {
			 for (Map.Entry<Path, String> entry : cache.entrySet()){
			  		System.out.println(" Hash Values------ " + entry.getValue());
			 }
			 
			cacheLock.unlock();
		
		  }
	}
		


	private void cacheFile(Path targetFile) throws IOException {
		cacheLock.lock();
		try{
		cache.put(targetFile, new String(Files.readAllBytes(targetFile)));
		} finally {
			cacheLock.unlock();
		}
	}



	protected abstract void replace( Path targetFile) throws IOException;

}

	


