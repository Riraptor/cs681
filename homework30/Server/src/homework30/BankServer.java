package homework30;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.io.PrintWriter;
import java.io.IOException;

public class BankServer{
	private static final int BANKPORT = 8888;
	private BankAccount account;
	private ServerSocket serverSocket;
	Calendar calendar = Calendar.getInstance();
	HashMap<Timestamp, String> temp = new HashMap<Timestamp, String>();
	ExecutorService workStealer = Executors.newWorkStealingPool();
	private static ThreadLocal<HashMap<Timestamp, String>> sessionHistory = new ThreadLocal<HashMap<Timestamp, String>>() {
	    @Override
	    protected HashMap<Timestamp, String> initialValue() {
	        return new HashMap<>();
	    }
	};
	public BankServer(){
		account = new BankAccount();
	}
	
	public void init(){

		 try(ServerSocket serverSocket = new ServerSocket(BANKPORT)){ 
				System.out.println("Socket created.");
				while(true){	
				
					System.out.println( "Listening for a connection on the local port " +
										serverSocket.getLocalPort() + "..." );
					Socket socket = serverSocket.accept();
					System.out.println( "\nA connection established with the remote port " + 
										socket.getPort() + " at " +
										socket.getInetAddress().toString() );
	
					workStealer.execute(new MyRunnable(socket));
				//	new Thread(new MyRunnable(socket)).start();  
					//executeCommand( socket );
				}
		 }catch(IOException exception){
			 exception.printStackTrace();
		 } finally {
			 	workStealer.shutdown();
		 }
	}
	
	
	private void executeCommand( Socket socket ){
		
		try{
			try{
				Scanner in = new Scanner( socket.getInputStream() );
				PrintWriter out = new PrintWriter( socket.getOutputStream() );
				System.out.println( "I/O setup done" );
				
				while(true){
					if( in.hasNext() ){
						String command = in.next();
						temp.put(new Timestamp(calendar.getTimeInMillis()), command);
						//System.out.println(temp);
						sessionHistory.set(temp);
					
						//System.out.println("CODE= " +sessionHistory.hashCode());
						if( command.equals("QUIT") ){
							
							System.out.println( "QUIT: Connection being closed." );
							out.println( "QUIT accepted. Connection being closed." );
							System.out.println("sessionHistory:::::::::");
							 for (Map.Entry<Timestamp, String> entry : sessionHistory.get().entrySet()){
							 		System.out.println(entry.getKey() +"   " +  entry.getValue());
							 }
							out.close();
							return;
						}
						
						accessAccount( command, in, out );
					}
				}
			}	finally{
				socket.close();
				System.out.println( "A connection is closed." );
			}
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
	}

	private void accessAccount( String command, Scanner in, PrintWriter out ){
		double amount;
		
		if( command.equals("DEPOSIT") ){
			amount = in.nextDouble();
			account.deposit( amount );
			System.out.println( "DEPOSIT: Current balance: " + account.getBalance() );
			out.println( "DEPOSIT Done. Current balance: " + account.getBalance() );
			//sessionHistory.get().put(new Timestamp(calendar.getTime().getTime()), command);
		}
		else if( command.equals("WITHDRAW") ){
			amount = in.nextDouble();
			account.withdraw( amount );
			System.out.println( "WITHDRAW: Current balance: " + account.getBalance() );
			out.println( "WITHDRAW Done. Current balance: " + account.getBalance() );
			//sessionHistory.get().put(new Timestamp(calendar.getTime().getTime()), command);
			
		}
		else if( command.equals("BALANCE") )
		{
			System.out.println( "BALANCE: Current balance: " + account.getBalance() );
			out.println( "BALANCE accepted. Current balance: " + account.getBalance() );
			//sessionHistory.get().put(new Timestamp(calendar.getTime().getTime()), command);
		}
		else if( command.equals("TERMINATE") )
		{
			System.out.println("##### TERMINATED ####");
			System.out.println( "BALANCE: Current balance: " + account.getBalance() );
			out.println( "BALANCE accepted. Current balance: " + account.getBalance() );
			//sessionHistory.get().put(new Timestamp(calendar.getTime().getTime()), command);
		    workStealer.shutdown();
		
		}
		else{
			System.out.println( "Invalid Command" );
			out.println( "Invalid Command. Try another command." );
			//sessionHistory.get().put(new Timestamp(calendar.getTime().getTime()), command);
		}
		out.flush();
	}
	
	private class MyRunnable implements Runnable {

		private Socket socket;
		public MyRunnable(Socket socket){
			this.socket = socket;
		}
		
			
		@Override
		public void run() {
			executeCommand(socket);
		}
	}
	
	public static void main(String[] args){
		BankServer server = new BankServer();
		server.init();
		
	
	}
}
