package homework22;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import java.util.concurrent.locks.ReentrantReadWriteLock;



public abstract class FileCache {
	ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
	public int max_cache_size = 3;
	public String targetFile;
	protected LinkedHashMap<Path, String> cache = new LinkedHashMap<Path, String>();
	protected HashMap<Path, Date> timeMap = new HashMap<Path, Date>();
	protected AccessCounter accessCounter = AccessCounter.getInstance();

	public FileCache() {	
	
	}

	public String fetch(Path targetFile) throws IOException {
		Date date = new Date();
		rwlock.writeLock().lock();
		try{
				timeMap.put(targetFile, date);
				if (cache.get(targetFile) == null) {			
						if (cache.size() < max_cache_size) {		
							cacheFile(targetFile);
							return "Returned Cache content of targetFile : " + cache.get(targetFile);
						}
						else{
							replace(targetFile);
							return "Returned Cache content of targetFile : " + cache.get(targetFile);
						} 
						
				}
		rwlock.readLock().lock();
		} catch (Exception e) {
			return "Things went wrong: " + e.getMessage();
		} finally {
			rwlock.writeLock().unlock();	
		
		}
		try {						
			return "Returned Cache content of targetFile : " + cache.get(targetFile);
		}finally {
			rwlock.readLock().unlock();
			
		}
		
		
	}
		


	private void cacheFile(Path targetFile) throws IOException {
		rwlock.writeLock().lock();
		try{
			cache.put(targetFile, new String(Files.readAllBytes(targetFile)));
		} finally {
			rwlock.writeLock().unlock();
		}
	}

	protected abstract void replace( Path targetFile) throws IOException;

}

	


