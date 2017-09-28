package homework14_1;

public class Guest implements Runnable {

	private SecurityGate gate;
	
	public Guest() {
		gate = SecurityGate.getInstance();
	}
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
		gate.enter();
		gate.exit();
		System.out.println("Total Guests inside = " + gate.getCount());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	
		}
	}

}
