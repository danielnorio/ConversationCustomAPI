package conversation;

public class DialogNode {

		private String go_to;
		private String output;
		private String parent;
		private String context;
		private String created;
		private String updated;
		private String metadata;
		private String conditions;
		private String description;
		private String dialog_node;
		private String previous_sibling;
		
		public DialogNode(String context, String conditions) {
			super();
			this.context = context;
			this.conditions = conditions;
		}

		public DialogNode(String go_to, String output, String parent, String context, String created, String updated,
				String metadata, String conditions, String description, String dialog_node, String previous_sibling) {
			this.go_to = go_to;
			this.output = output;
			this.parent = parent;
			this.context = context;
			this.created = created;
			this.updated = updated;
			this.metadata = metadata;
			this.conditions = conditions;
			this.description = description;
			this.dialog_node = dialog_node;
			this.previous_sibling = previous_sibling;
		}
		
		public String getGo_to() { return go_to;}
		public void setGo_to(String go_to) { this.go_to = go_to;}
		
		public String getOutput() { return output;}
		public void setOutput(String output) { this.output = output;}
		
		public String getParent() { return parent;}
		public void setParent(String parent) { this.parent = parent;}
		
		public String getContext() { return context;}
		public void setContext(String context) { this.context = context;}
		
		public String getCreated() { return created;}
		public void setCreated(String created) { this.created = created;}
		
		public String getUpdated() {return updated; }
		public void setUpdated(String updated) { this.updated = updated;}
		
		public String getMetadata() { return metadata;}
		public void setMetadata(String metadata) { this.metadata = metadata;}
		
		public String getConditions() { return conditions;}
		public void setConditions(String conditions) { this.conditions = conditions;}
		
		public String getDescription() { return description;}
		public void setDescription(String description) { this.description = description;}
		
		public String getDialog_node() { return dialog_node;}
		public void setDialog_node(String dialog_node) { this.dialog_node = dialog_node;}
		
		public String getPrevious_sibling() { return previous_sibling;}
		public void setPrevious_sibling(String previous_sibling) { this.previous_sibling = previous_sibling;}
		
}
