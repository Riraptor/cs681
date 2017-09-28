package homework21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map.Entry;

public class LFUFileCache extends FileCache {
	
	Path leastUsedPath;
	@Override
	protected void replace(Path targetFile) throws IOException {
		int least = Integer.MAX_VALUE;
		cacheLock.lock();
		try {
			//System.out.println(accessCounter.getAccessCounter());
			for (Entry<Path, String> entry : cache.entrySet()){
					if(accessCounter.getCount(entry.getKey()) < least) {
						least = accessCounter.getCount(entry.getKey());
						leastUsedPath = entry.getKey();
					}
  		}
		System.out.println("LEAST USED PATH IS : " + leastUsedPath);
		//accessCounter.removeCounter(leastUsedPath);
		cache.remove(leastUsedPath);
		cache.put(targetFile, new String(Files.readAllBytes(targetFile)));
		
		} finally {
			 cacheLock.unlock();
		}
	}

}
