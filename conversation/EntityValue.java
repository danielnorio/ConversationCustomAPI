package conversation;

import java.util.ArrayList;

public class EntityValue {
	private String value;
	private String created;
	private String updated;
	private String metadata;
	
	private ArrayList<String> synonyms = new ArrayList<String>();
	private boolean focused = true;
	
	public EntityValue(String value, String created, String updated, String metadata,
			boolean focused) {
		super();
		this.value = value;
		this.created = created;
		this.updated = updated;
		this.metadata = metadata;
		this.focused = focused;
	}

	public EntityValue(String value) { this.value = value;}
	
	public String getValue() { return value;}
	public void setValue(String value) { this.value = value;}
	
	public ArrayList<String> getSynonyms() { return synonyms;}
	public void setSynonyms(ArrayList<String> synonyms) { this.synonyms = synonyms;}
	public void addSynonym(String synonym) { this.synonyms.add(synonym); }
	public void removeSynonym(String synonym) {this.synonyms.remove(synonym); }

	public String getCreated() { return created;}
	public void setCreated(String created) { this.created = created;}

	public String getUpdated() { return updated;}
	public void setUpdated(String updated) { this.updated = updated;}

	public String getMetadata() { return metadata;}
	public void setMetadata(String metadata) { this.metadata = metadata;}

	public boolean isFocused() { return focused;}
	public void setFocused(boolean focused) { this.focused = focused;}
	
}
