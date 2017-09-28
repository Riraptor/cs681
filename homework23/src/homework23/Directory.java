package homework23;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Directory extends FSElement{
	private String name;
	private String owner;
	private Date created;
	private Date lastModified;
	int Size;
	private ArrayList<FSElement> children = new ArrayList<FSElement>();
	//public FileSystem fileSystem = FileSystem.getFileSystem();
	
	public Directory(Directory parent, String name, String owner, Date created, Date lastModified) {
			this.name = name;
			this.owner = owner;
			this.created = created;
			this.lastModified = lastModified;
		
	}
	 
	@Override
	public boolean isFile(){
		return false;
	}
	public String getName(){
		return this.name;
	}
	
	@Override
	public int getSize(){
		int Size = 0;
		Iterator<FSElement> childIterator = children.iterator();
		while(childIterator.hasNext()) {
		
			FSElement el = childIterator.next();
			    Size = Size + el.getSize();
			  
	   }
		return Size;
	    
	}

	public ArrayList<FSElement> getChildren() {
	
		return children;
		
	}
	public void appendChild(FSElement child){
		children.add(child);
	}
	
	public void PrintSubStructures() {
	
		Iterator<FSElement> childIterator = getChildren().iterator();
		while(childIterator.hasNext()) {
		
			FSElement e = childIterator.next();
			 if(e.isFile()){
			    System.out.println(" -> "+ e.getName());
			 }
			 else {
				 System.out.println(" -----");
				 System.out.println("|"+e.getName() +"|");
				 System.out.println(" -----");
				 ((Directory) e).PrintSubStructures();
			 }
		}
		
	}

}
