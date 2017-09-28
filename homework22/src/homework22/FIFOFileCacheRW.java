package homework22;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class FIFOFileCacheRW extends FileCache {

	public FIFOFileCacheRW() throws IOException {
		super();
	}
	
	@Override
	protected  void replace( Path targetFile) throws IOException {
		rwlock.writeLock().lock();
		try {
		cache.remove(cache.entrySet().iterator().next().getKey());
		cache.put(targetFile, new String(Files.readAllBytes(targetFile)));
		} finally {
			rwlock.writeLock().unlock();
		}
	}
	
}
