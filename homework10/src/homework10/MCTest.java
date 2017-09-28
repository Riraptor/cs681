package homework10;

import java.util.ArrayList;

class MCTest{
  public static void main(String[] args) throws Exception {
    ArrayList<Thread> threads = new ArrayList<Thread>();
  
    final long nTimes  = 1000;
    final int nThread = 100;
    long t1=System.nanoTime();
    for (int i = 0; i < nThread; i++) {
      /*
    	Thread t = new Thread(
    		  new Runnable() {
    		  	public void run() {
    		  		int n = 25;
    		  		for (long j = 0; j < nTimes; j++) {
    		  			n*= 25;
    		  		}
    		  	}
    		  });*/
    	
    	//Replacing the anonymous class with a lambda expression:
    	Runnable r = () -> {
						  		int n = 25;
						  		for (long j = 0; j < nTimes; j++) {
						  			n*= 25;
						  		}
				    	  };
      Thread t = new Thread(r);
      threads.add(t);
      t.start();
  
    }

    for (Thread t: threads) {
      t.join();
    }
   long diff= System.nanoTime() - t1;
   System.out.println("With # of threads = 20");
    System.out.println("Test Completed in  " + diff+ " Nano sec");
  }
}
