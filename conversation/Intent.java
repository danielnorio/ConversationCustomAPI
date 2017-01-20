package conversation;

import java.util.ArrayList;

public class Intent {

	private String name;
	private String description;
	private ArrayList<IntentExample> examples = new ArrayList<IntentExample>();
	private String created;
	private String updated;
	
	public Intent(String name, String description, String created, String updated)
	{
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
	}
	
	public Intent(String name, String description)
	{
		this.name = name;
		this.description = description;
	}
	
	public Intent(String name) 
	{
		this.name = name;
	}
	
	public String getName() { return name;}
	public void setName(String name) { this.name = name;}
	
	public String getDescription() { return description;}
	public void setDescription(String description) { this.description = description;}
	
	public ArrayList<IntentExample> getExamples() { return examples;}
	public void setExamples(ArrayList<String> examples) { 
		for (String example : examples) 
			this.examples.add(new IntentExample(example));
	}
	public void setIntentExamples(ArrayList<IntentExample> examples) { 
		this.examples = examples;
	}
	public void addExample(String example) { this.examples.add(new IntentExample(example));}

	public String getCreated() { return created;}
	public void setCreated(String created) { this.created = created;}

	public String getUpdated() { return updated;}
	public void setUpdated(String updated) { this.updated = updated;}
	
}
