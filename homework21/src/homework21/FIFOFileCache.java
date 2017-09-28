package homework21;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class FIFOFileCache extends FileCache {

	public FIFOFileCache() throws IOException {
		super();
	}
	
	@Override
	protected  void replace( Path targetFile) throws IOException {
		cacheLock.lock();
		try {
		cache.remove(cache.entrySet().iterator().next().getKey());
		cache.put(targetFile, new String(Files.readAllBytes(targetFile)));
		} finally {
			cacheLock.unlock();
		}
	}
	
}
