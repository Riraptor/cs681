package homework22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map.Entry;

public class LFUFileCacheRW extends FileCache {
	
	Path leastUsedPath;
	@Override
	protected void replace(Path targetFile) throws IOException {
		int least = Integer.MAX_VALUE;
		rwlock.writeLock().lock();
		try {
			//System.out.println(accessCounter.getAccessCounter());
			for (Entry<Path, String> entry : cache.entrySet()){
					if(accessCounter.getCount(entry.getKey()) < least) {
						least = accessCounter.getCount(entry.getKey());
						leastUsedPath = entry.getKey();
					}
  		}
		System.out.println( "LEAST USED PATH IS : " + leastUsedPath);
		cache.remove(leastUsedPath);
		cache.put(targetFile, new String(Files.readAllBytes(targetFile)));
		
		} finally {
			 rwlock.writeLock().unlock();
		}
	}

}
