package homework23;

import java.util.ArrayList;

public class FileCrawler implements Runnable{
	
	private Directory dir;
	private FileQueue queue ;
	private volatile boolean done = false;
	public FileCrawler(Directory dir, FileQueue queue) {
		this.dir = dir;
		this.queue = queue;
	}
	public void setDone() {
		done = true;
	}
	
	public void crawl(Directory dir) throws InterruptedException {
		for (FSElement e: dir.getChildren()) {
			if(e instanceof File) {
				queue.put((File) e);
				System.out.println(e.getName() + " Crawled & stored to FileQueue");
			}
			if(e instanceof Directory) {
				crawl((Directory)e);
			}
		}
	}
	@Override
	public void run() {
		while(true) {
			try {
				if(done) {
					System.out.println(Thread.currentThread() + "is done");
					break;
				}
				crawl(this.dir);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread() +" is interrupted");
			}
		}
	}
	

}
