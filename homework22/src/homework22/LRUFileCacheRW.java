package homework22;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.Map.Entry;



public class LRUFileCacheRW extends FileCache {

	private Date leastRecentDate;

	Path LeastRecentPath;
	public  LRUFileCacheRW() {
	
		
	}

	@Override
	protected void replace(Path targetFile) throws IOException {
		/*
		 * One Iteration over the timeMap to decide which one of the paths was accessed the last. (Linear search)
		 */
		leastRecentDate = new Date(3000,1,1);
		rwlock.writeLock().lock();
		try {
		for (Entry<Path, Date> entry : timeMap.entrySet()){
	
	  			if(leastRecentDate.after(entry.getValue())) { 
	  				leastRecentDate = entry.getValue();
	  				LeastRecentPath  = entry.getKey();
	 	  			}

	  	}

		timeMap.remove(LeastRecentPath);
		cache.remove(LeastRecentPath);
		cache.put(targetFile, new String(Files.readAllBytes(targetFile)));
		leastRecentDate =new Date(3000,1,1);
		} finally {
			rwlock.writeLock().unlock();
		  }
	}
}


