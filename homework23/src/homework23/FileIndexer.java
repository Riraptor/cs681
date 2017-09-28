package homework23;

public class FileIndexer implements Runnable {

	private FileQueue queue;
	private Directory dir;
	private volatile boolean done = false;
	
	public FileIndexer(Directory dir, FileQueue queue) {
		this.dir = dir;
		this.queue = queue;
		
	}
	
	public void indexFile(File file) {
		System.out.println("\n");
		System.out.println("File Index sucessfull");
		System.out.println("File name : "+  file.getName());
		System.out.println("File Owner: " + file.getOwner());
		System.out.println("Size  :" + file.getSize());
		
	}
	public void setDone() {
		done = true;
	}
	
	@Override
	public void run() {
			while(true) {
				try {
					if(done) {
						System.out.println(Thread.currentThread() + "is done");
						break;
					}
					
						indexFile(queue.get());
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread() +" is interrupted");
				}
			}
	}

}
