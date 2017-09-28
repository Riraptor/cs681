package homework23;


import java.util.Date;

public class FileSystem {
	static Date d1 = new Date();
	static Date m1 = new Date();
	static FileSystem instance = null;
	static Directory rootDir = new Directory(null, "RootDir" ,"Super", d1, m1);

	private  FileSystem(){
		
	}
	public static FileSystem getFileSystem(){
		if (instance == null) {
			instance = new FileSystem();
		}
		return instance;
	}
	public void showAllElements(){
		getRootDir().PrintSubStructures();

	}
	
	public Directory getRootDir() {
		return rootDir;
	}
	
	public Directory MakeDir(Directory parent, String name, String owner, Date d, Date Lmodified){
		Directory dir = new Directory (parent, name, owner, d, Lmodified);
		return dir;
	}
	
	public File MakeFile(Directory parent, String name, String owner, Date d, Date Lmodified, int size){
		File file = new File (parent, name, owner, d, Lmodified, size);
	return file;
	}
	
	public void AddChild(Directory parent, FSElement child){
		parent.appendChild(child);
	}
	public int GetTotalSizeOfDir(FSElement element){
		return element.getSize();
	}
	
	public Link MakeLink(Directory parent, String name, String owner, FSElement target) {
		Link link = new Link(parent, name, owner, target);
		return link;
	}
	
}
