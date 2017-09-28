package Soultion3a;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/*
 * Solution Using AccountHandler
 */
public class ThreadSafeBankAccount2_Solution3a{
	private double balance = 0;
	private ReentrantLock lock;
	private Condition sufficientFundsCondition, belowUpperLimitFundsCondition;
	private ThreadSafeBankAccount2_Solution3a account;
	//private double accountNumber;
	
	public ThreadSafeBankAccount2_Solution3a(){
		lock = new ReentrantLock();
		sufficientFundsCondition = lock.newCondition();
		belowUpperLimitFundsCondition = lock.newCondition();
		account =  this;
		//this.accountNumber = accountNumber;
	}
	
	//public  double getAcctNum() {
	//	return accountNumber;
	//}
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
	
	
	public ReentrantLock getLock() {
		return lock;
	}
	
	public static void main(String[] args){
		System.out.println("-------------------SOLUTION 5-----------------------");
		ThreadSafeBankAccount2_Solution3a AccountA = new ThreadSafeBankAccount2_Solution3a();
		ThreadSafeBankAccount2_Solution3a AccountB = new ThreadSafeBankAccount2_Solution3a();
		
		DepositRunnable DA = AccountA.new DepositRunnable();
		WithdrawRunnable WA = AccountA.new WithdrawRunnable();
		DepositRunnable DB = AccountB.new DepositRunnable();
		WithdrawRunnable WB = AccountB.new WithdrawRunnable();
		
		AccountHandler ATB = new AccountHandler(AccountA, AccountB, 2.0);
		AccountHandler BTA = new AccountHandler(AccountA, AccountB, 2.0);
		
		
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
	

	public double getBalcnce() {
	return balance;
	}
	
}
