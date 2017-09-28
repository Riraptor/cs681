package homework4;

public class Client {
	public static void main(String[] arges) {
		 Observable observable = new Observable();
		 observable.setChanged(); 
		 observable.addObserver( (Observable o, Object obj)-> {System.out.println(obj);} );
		 observable.notifyObservers("Hello World!");
	
	}
}
