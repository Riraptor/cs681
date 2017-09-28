package homework23;


import java.util.Date;


public class FSElement {
	private Directory parent;
	private String name;
	private String owner;
	private Date created;
	private Date lastModified;
	private int size;

	public FSElement(){
		
	}
	
	public FSElement(Directory parent, String name, String owner, 
					 Date created, Date lastModified, int size) {
		this.name = name;
		this.owner = owner;
		this.created = created;
		this.lastModified = lastModified;
		this.size = size;
		
	}
	
	public Directory  getParent(){
		return this.parent;
	}
	
	public boolean isFile(){
		return true;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getOwner(){
		return this.owner;
	}
	
	public Date getDateCreated(){
		return this.created;
	}
	public Date getLastModified(){
		return this.lastModified;
	}


	
	
	
}
