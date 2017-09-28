package homework23;

import java.util.Date;

public class File extends FSElement{
	private String name;
	private String owner;
	private Date created;
	private Date lastModified;
	private int size;
	public File(Directory parent, String name, String owner, Date created, Date lastModified, int size) {
		this.name = name;
		this.owner = owner;
		this.created = created;
		this.lastModified = lastModified;
		this.size = size;
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
}
