package conversation;

import java.util.ArrayList;

public class EntityValue {
	private String value;
	private ArrayList<String> synonyms = new ArrayList<String>();
	private boolean focused = true;
	
	public EntityValue(String value) { this.value = value;}
	
	public String getValue() { return value;}
	public void setValue(String value) { this.value = value;}
	
	public ArrayList<String> getSynonyms() { return synonyms;}
	public void setSynonyms(ArrayList<String> synonyms) { this.synonyms = synonyms;}
	public void addSynonym(String synonym) { this.synonyms.add(synonym); }
	public void removeSynonym(String synonym) {this.synonyms.remove(synonym); }

	public boolean isFocused() { return focused;}
	public void setFocused(boolean focused) { this.focused = focused;}
	
}
