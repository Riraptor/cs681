package homework7;

import java.util.Date;

public class GreetingRunnable implements Runnable{
	private String greeting;
	
	public GreetingRunnable (String aGreeting) {
		greeting = aGreeting;
	}
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			Date now = new Date();
			System.out.println(now + " " + greeting);
			
		}
	}
	

}
