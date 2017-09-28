package Solution1;



import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/*
 * Static Locking to Remove dreadlock. This solution is easy but Transfers/ Deposite/ Withdrawal on different accounts are 
 * performed sequentially.
 */
public class ThreadSafeBankAccount2_Solution1{
	private double balance = 0;
	private static ReentrantLock lock;
	private static Condition sufficientFundsCondition, belowUpperLimitFundsCondition;
	private ThreadSafeBankAccount2_Solution1 account;
	
	public ThreadSafeBankAccount2_Solution1(){
		lock = new ReentrantLock();
		sufficientFundsCondition = lock.newCondition();
		belowUpperLimitFundsCondition = lock.newCondition();
		account =  this;
	}
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (d): current balance: " + balance);
			while(balance >= 300){
				System.out.println(Thread.currentThread().getId() + 
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().getId() + 
					" (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (w): current balance: " + balance);
			while(balance <= 0){
				System.out.println(Thread.currentThread().getId() + 
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId() + 
					" (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void transfer(ThreadSafeBankAccount2_Solution1 destination, double amount) {	
		lock.lock();
		try{
				if( balance < amount) {
					System.out.println("@@@@@@@@@@@@Not Enough funds");
				}
				else {
					withdraw(amount);
					destination.deposit(amount);
					System.out.println("!!!!!!!!!!!!!!!!!!!!Funds Transfered");
				}
		} finally {
		lock.unlock();
		}
	}
	
	public static void main(String[] args){
		/*
		ThreadSafeBankAccount2_Solution1 bankAccount = new ThreadSafeBankAccount2_Solution1();
		for(int i = 0; i < 5; i++){
			new Thread( bankAccount.new DepositRunnable() ).start();
		}
		new Thread( bankAccount.new WithdrawRunnable() ).start();
		new Thread( bankAccount.new TransferRunnable(bankAccount,100) ).start();
		*/
		System.out.println("-------------------SOLUTION 1-----------------------");
		ThreadSafeBankAccount2_Solution1 AccountA = new ThreadSafeBankAccount2_Solution1();
		ThreadSafeBankAccount2_Solution1 AccountB = new ThreadSafeBankAccount2_Solution1();
		
		DepositRunnable DA = AccountA.new DepositRunnable();
		WithdrawRunnable WA = AccountA.new WithdrawRunnable();
		DepositRunnable DB = AccountB.new DepositRunnable();
		WithdrawRunnable WB = AccountB.new WithdrawRunnable();
		
		TransferRunnable ATB = AccountA.new TransferRunnable(AccountB, 1);
		TransferRunnable BTA = AccountB.new TransferRunnable(AccountA, 2);
		Thread threadDA = new Thread(DA);
		Thread threadWA = new Thread(WA);
		Thread threadDB = new Thread(DB);
		Thread threadWB = new Thread(WB);
		
		Thread threadATB = new Thread(ATB);
		Thread threadBTA = new Thread(BTA);
		threadDA.start();
		threadWA.start();
		threadDB.start();
		threadWB.start();
		threadATB.start();
		threadBTA.start();
		
		
		
		
		
	}

	private class DepositRunnable implements Runnable{
		public void run(){
			try{
				for(int i = 0; i < 10; i++){
					account.deposit(100);
					Thread.sleep(2);
				}
			}
			catch(InterruptedException exception){}
		}
	}
	
	private class WithdrawRunnable implements Runnable{
		public void run(){
			try{
				for(int i = 0; i < 10; i++){
					account.withdraw(10);
					Thread.sleep(2);
				}
			}
			catch(InterruptedException exception){}
		}
	}
	
	private class TransferRunnable implements Runnable {
		ThreadSafeBankAccount2_Solution1 destination;
		double amount;
		public TransferRunnable(ThreadSafeBankAccount2_Solution1 destination, double amount) {
			this.destination = destination;
			this.amount = amount;
		}
		public void run(){
			try{
				for(int i = 0; i < 10; i++) {
					account.transfer(destination, amount);
					Thread.sleep(2);
				}
			}
			catch(InterruptedException exception){}
		}
	}
	
}

