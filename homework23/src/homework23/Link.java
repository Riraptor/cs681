package homework23;

public class Link extends FSElement{

	private FSElement target;
	String name;
	public Link(Directory parent, String name, String owner, FSElement target){
		this.target = target;
		this.name = name;
	}
	
	public int getSize(){
		return 0;
	}
	
	public int getTargetSize(){
		return target.getSize();
	}
	
	public boolean isFile(){
		return true;
	}
	public String getName(){
		return this.name + " (Link)";
	}
}
