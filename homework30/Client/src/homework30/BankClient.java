package homework30;



import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;

public class BankClient{
	private static final int BANKPORT = 8888;
	private Socket socket;
	private Scanner in;
	private PrintWriter out;

	public void init(){
		try(Socket socket = new Socket("localhost", BANKPORT)){ 
			
				System.out.println( "Socket created on the local port " +
									socket.getLocalPort() );
				System.out.println( "A connection established with the remote port " +
									socket.getPort() + " at " +
									socket.getInetAddress().toString() );
	
				in = new Scanner( socket.getInputStream() );
				out = new PrintWriter( socket.getOutputStream() );
				System.out.println( "I/O setup done." );
				sendCommands();
		}catch(IOException exception){
			exception.printStackTrace();
		}
	}
	
	public void sendCommands(){
		sendCommand( "BALANCE\n" );
		System.out.println( getResponse() );
		
		sendCommand( "DEPOSIT 100\n" );
		System.out.println( getResponse() );
		
		sendCommand( "WITHDRAW 50\n" );
		System.out.println( getResponse() );
		
		sendCommand("TERMINATE\n");
		sendCommand( "QUIT\n" );		
	}
	
	public void sendCommand( String command ){
		System.out.print( "Sending " + command );
		out.print( command );
		out.flush();
		if( command.equals("QUIT") ){
			try{
				socket.close();
				System.out.println( "A connection closed." );
			}
			catch (IOException exception){
				exception.printStackTrace();
			}
		}
	}
	
	public String getResponse(){
		return in.nextLine();
	}

	public static void main(String[] args) throws InterruptedException{
		
	
	try {
		BankClient clientA = new BankClient();
		clientA.init();
	
		BankClient clientB = new BankClient();
		clientB.init();
	
		BankClient clientC = new BankClient();
		clientC.init();

		BankClient clientD = new BankClient();
		clientD.init();
	} catch (NoSuchElementException e) {
		System.out.println("############### TERMINATED ###########");
	}
		
	}
}