package conversation;

import java.util.ArrayList;

public class Entity {
	private String entityName;
	private ArrayList<EntityValue> values = new ArrayList<EntityValue>();
	private String created;
	private String updated;
	private String type;
	private String source;
	private String open_list;
	private String description;
	
	public Entity(String entityName, String created, String updated, String type,
			String source, String open_list, String description) {
		super();
		this.entityName = entityName;
		this.values = values;
		this.created = created;
		this.updated = updated;
		this.type = type;
		this.source = source;
		this.open_list = open_list;
		this.description = description;
	}

	public Entity(String entityName) { this.entityName = entityName; }
	
	public Entity(String entityName, String created, String updated) 
	{ 
		this.entityName = entityName;
		this.created = created;
		this.updated = updated;
	}

	public String getEntityName() { return entityName;}
	public void setEntityName(String entityName) { this.entityName = entityName;}
	
	public ArrayList<EntityValue> getValues() { return values;}
	public void setValues(ArrayList<EntityValue> values) { this.values = values;}
	public void addValue(EntityValue value) { this.values.add(value);}
	public void removeValue(EntityValue value) { this.values.remove(value);}
	
	public String getCreated() { return created;}
	public void setCreated(String created) { this.created = created;}
	
	public String getUpdated() { return updated;}
	public void setUpdated(String updated) { this.updated = updated;}

	public String getType() { return type;}
	public void setType(String type) {this.type = type;}

	public String getSource() { return source;}
	public void setSource(String source) { this.source = source;}

	public String getOpen_list() { return open_list;}
	public void setOpen_list(String open_list) { this.open_list = open_list;}

	public String getDescription() { return description;}
	public void setDescription(String description) { this.description = description;}

}
