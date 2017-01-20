package conversation;

public class IntentExample {

	private String text;
	private String created;
	private String updated;
	
	public IntentExample(String text) { this.text = text;}
	public IntentExample(String text, String created, String updated) 
	{ 
		this.text = text;
		this.created = created;
		this.updated = updated;
	}
	
	public String getText() { return text;}
	public void setText(String text) { this.text = text;}
	
	public String getCreated() { return created;}
	public void setCreated(String created) { this.created = created;}
	
	public String getUpdated() { return updated;}
	public void setUpdated(String updated) { this.updated = updated;}

}
