package conversation;

import java.util.ArrayList;

public class DialogNode {

		private String go_to;
		private String output;
		private String parent = "null";
		private String context;
		private String created;
		private String updated;
		private String metadata;
		private String conditions;
		private String description;
		private String dialog_node;
		private String previous_sibling = "null";
		
		// Navigation properties
		private DialogNode previousSibling;
		private DialogNode parentNode;
		
		// Extra navigation properties
		private ArrayList<DialogNode> childs = new ArrayList<DialogNode>();
		private DialogNode nextSibling;
		
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
		public DialogNode setGo_to(String go_to) { this.go_to = go_to; return this;}
		
		public String getOutput() { return output;}
		public DialogNode setOutput(String output) { this.output = output; return this;}
		
		public String getParent() { return parent;}
		public DialogNode setParent(String parent) { this.parent = parent; return this;}
		
		public String getContext() { return context;}
		public DialogNode setContext(String context) { this.context = context; return this;}
		
		public String getCreated() { return created;}
		public DialogNode setCreated(String created) { this.created = created; return this;}
		
		public String getUpdated() {return updated; }
		public DialogNode setUpdated(String updated) { this.updated = updated; return this;}
		
		public String getMetadata() { return metadata;}
		public DialogNode setMetadata(String metadata) { this.metadata = metadata; return this;}
		
		public String getConditions() { return conditions;}
		public DialogNode setConditions(String conditions) { this.conditions = conditions; return this;}
		
		public String getDescription() { return description;}
		public DialogNode setDescription(String description) { this.description = description; return this;}
		
		public String getDialog_node() { return dialog_node;}
		public DialogNode setDialog_node(String dialog_node) { this.dialog_node = dialog_node; return this;}
		
		public String getPrevious_sibling() { return previous_sibling;}
		public DialogNode setPrevious_sibling(String previous_sibling) { this.previous_sibling = previous_sibling; return this;}
		
		// Methods for navigation
		public DialogNode getParentNode() { return parentNode;}
		public DialogNode setParentNode(DialogNode parent) { 
			this.parentNode = parent;
			this.parent = parent.getDialog_node();
			return this;
		}

		public DialogNode getPreviousSibling() { return previousSibling;}
		public DialogNode setPreviousSibling(DialogNode ps) {
			/*
			///Copy parent relationship to object
			ps.setParent(this.parent);
			ps.parentNode = this.parentNode;
			//Set this as next sibling
			ps.setNextSibling(this);
			//Set previous sibling 
			this.setPrevious_sibling(ps.getPrevious_sibling());
			this.setPreviousSibling(ps);
			//Set as child of parent
			if (this.parentNode.getChildIndex(this.getDialog_node()) > 0)
				this.parentNode.getChilds().add(this.parentNode.getChildIndex(this.getDialog_node()), ps);
			else
				this.parentNode.addChild(ps);
			*/
			return this;
		}

		
		public ArrayList<DialogNode> getChilds() { return childs;}
		public DialogNode getChild(String id) {
			for (DialogNode child : this.childs) {
				if (child.getDialog_node() == id) 
					return child;
			}
			return null;
		}
		
		public int getChildIndex(String id) {
			for (int i = 0; i < this.childs.size(); i++) {
				if (this.childs.get(i).getDialog_node() == id) return i;
			}
			return -1;
		}
		public DialogNode addChild(DialogNode child) {
			//Supposes that the child is already parent of this

			//Supposes that the child already has relation with sibling
	
			//Simply add child to this node
			this.childs.add(child);
			return this;
		}
		
		public DialogNode getNextSibling() { return nextSibling;}
		public DialogNode setNextSibling(DialogNode nextSibling) { this.nextSibling = nextSibling; return this;}
		
}
