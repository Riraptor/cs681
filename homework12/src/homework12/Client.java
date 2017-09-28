package homework12;

public class Client {
	public static void main(String[] args) throws InterruptedException {
		Editor editor1 = new Editor();
		AutoSaver autoSave1 = new AutoSaver();
		
		Thread editorThread  = new Thread(editor1);
		Thread autoSaverThread = new Thread(autoSave1);
		
		editorThread.start();
		autoSaverThread.start();

		Thread.sleep(5000);
		
		autoSave1.setDone();
		editor1.setDone();

	

		
	}
}
