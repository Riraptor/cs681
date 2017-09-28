package homework23;



import java.util.Date;


public class TestMain {
	public static void main(String[] args) throws InterruptedException{
		Date d1 = new Date();
		Date m1 = new Date();
		
	   
		FileSystem fileSystem = FileSystem.getFileSystem();
		
		FileQueue queue= new FileQueue(5);
		//fileSystem.setRootDir(new Directory(null, "RootDir" ,"Rizan", d1, m1));
		Directory root = fileSystem.getRootDir();
	
		//------------------------------------
		Directory DriveC = fileSystem.MakeDir(root, "Drice C: ", "user", d1, m1);
		Directory DriveD = fileSystem.MakeDir(root, "Drive D: ", "user", d1, m1);
		File a = fileSystem.MakeFile(DriveC, "a", "user", d1, m1, 20);
		File b = fileSystem.MakeFile(DriveC, "b", "user", d1, m1, 20);
		File c = fileSystem.MakeFile(DriveC, "c", "user", d1, m1, 20);
		File d = fileSystem.MakeFile(DriveD, "d", "user", d1, m1, 20);
		Directory picture = fileSystem.MakeDir(DriveD, "Picture", "user", d1, m1);
		File e = fileSystem.MakeFile(picture, "e", "user", d1, m1, 20);
		File f = fileSystem.MakeFile(picture, "f", "user", d1, m1, 20);
		Link x = fileSystem.MakeLink(DriveD,"x","user",DriveC);
		Link y = fileSystem.MakeLink(picture, "y", "user", e);
		fileSystem.AddChild(picture,y);
		fileSystem.AddChild(DriveD,x);
		fileSystem.AddChild(root, DriveC);
		fileSystem.AddChild(root, DriveD);
		fileSystem.AddChild(DriveC, a);
		fileSystem.AddChild(DriveC, b);
		fileSystem.AddChild(DriveC, c);
		fileSystem.AddChild(DriveD, d);
		fileSystem.AddChild(DriveD, picture);
		fileSystem.AddChild(picture, e);
		fileSystem.AddChild(picture, f);
		
		
		
		fileSystem.showAllElements();
		System.out.println("Size of target of x "+ x.getTargetSize());
		System.out.println("Size of link x" + x.getSize());
		System.out.println("Size of target of y "+ y.getTargetSize());
		System.out.println("Size of link y " + y.getSize());
		System.out.println("system = " + fileSystem.GetTotalSizeOfDir(DriveC));
		System.out.println("home = " + fileSystem.GetTotalSizeOfDir(DriveD));
		System.out.println("picture = " + fileSystem.GetTotalSizeOfDir(picture));
		System.out.println("Total size = " + fileSystem.GetTotalSizeOfDir(root));

		FileCrawler crawlerC = new FileCrawler(DriveC,queue) ;
		FileCrawler crawlerD = new FileCrawler(DriveD,queue) ;
		FileIndexer indexer = new FileIndexer(root,queue);
		
		Thread thread1 = new Thread(crawlerC);
		Thread thread2 = new Thread(indexer);
		Thread thread3 = new Thread(crawlerD);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		Thread.sleep(1000);
		
		thread1.interrupt();
		thread2.interrupt();
		thread3.interrupt();
		crawlerD.setDone();
		crawlerC.setDone();
		indexer.setDone();	
		Thread.sleep(1000);
		queue.printQueue();
		
		
	}
}
