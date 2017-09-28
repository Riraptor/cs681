package homework14_2;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class SecurityGate {
	private AtomicInteger counter = new AtomicInteger(0);
	private static ReentrantLock slock = new ReentrantLock();
	private SecurityGate() {};
	private static SecurityGate instance = null;
	
	public static SecurityGate getInstance() {
		slock.lock();
		try {
			if (instance == null) {
				instance = new SecurityGate();
			}
		} finally {
			slock.unlock();
		}
		return instance;
	}
	public void enter() {
		
			System.out.println("guest entered");
			counter.updateAndGet((int i) -> ++i);
		
	}
	
	public void exit() {
		
			System.out.println("guest existed");
			counter.updateAndGet((int i) -> --i);
			
		
	}
	
	public AtomicInteger getCount() {
		return counter;
	}
}
