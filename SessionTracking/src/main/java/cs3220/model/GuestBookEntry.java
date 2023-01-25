package cs3220.model;

public class GuestBookEntry {
	
	static int idSeed = 1;
	private String name;
	private String message;
	private int id;
	
	public GuestBookEntry(String name, String message) {
		this.id = idSeed++ ;
		this.name = name;
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
