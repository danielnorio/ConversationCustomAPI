package conversation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dialog {

	// Jagged array for easy manipulation of tree sibling
	private ArrayList<ArrayList<DialogNode>> nodesTree = new ArrayList<ArrayList<DialogNode>>();
	
	public ArrayList<ArrayList<DialogNode>> getArvore() {
		return nodesTree;
	}


	public Dialog(ArrayList<DialogNode> nodes) 
	{
		Map<String, DialogNode> links = new HashMap<String, DialogNode>();
		//Add all nodes to map
		for (DialogNode d : nodes)
			links.put(d.getDialog_node(), d);
		//Add all links to nodes (parent and previous sibling)
		for (DialogNode d : nodes) {
			if (d.getPrevious_sibling() != null)
				d.setPreviousSibling(links.get(d.getPrevious_sibling()));
			if (d.getParent() != null)
				d.setParentNode(links.get(d.getParent()));
		}
		
		//Store nodes inside jagged array
		//Takes advantage of the order that Conversation returns the list of nodes
		int depth = 0;
		ArrayList<String> parentsTraversed = new ArrayList<String>();
		for (DialogNode d : nodes) {
			//If node belongs to root set depth = 0
			if (d.getParent() == null) {
				depth = 0;
				parentsTraversed.clear();
				parentsTraversed.add(d.getDialog_node());
			}
			// Else if node is coming down the tree increase depth
			else if (d.getParent() == parentsTraversed.get(parentsTraversed.size()-1)) {
				depth++;
				parentsTraversed.add(d.getDialog_node());
			}
			// Else if node is in the same branch select that node
			else if (d.getPrevious_sibling() == parentsTraversed.get(parentsTraversed.size()-1)){
				parentsTraversed.set(parentsTraversed.size()-1, d.getDialog_node());
			}
			// Else node is comming up the tree
			else {
				// Remove post nodes from travel list
				int keep = parentsTraversed.indexOf(d.getParent());
				for (int i = parentsTraversed.size()-1; i > keep; i--)
				{
					parentsTraversed.remove(i);
				}
				depth = parentsTraversed.size();
				parentsTraversed.add(d.getDialog_node());				
			}
			// Add node to correctly depth in matrix
			if (nodesTree.size() < depth + 1) {
				nodesTree.add(new ArrayList<DialogNode>());
			}
			nodesTree.get(depth).add(d);
		}
		
		
		
	}
	
	// Generate a valid node id
	// If the id supplied is already valid returns the same
	public String validNodeId(String id) throws InterruptedException {
		ArrayList<String> forbiddenIds = new ArrayList<String>();
		boolean isValid = true;
		// Generate list of currents ids and check if supplied id is valid
		for (ArrayList<DialogNode> branch : nodesTree) {
			for (DialogNode node : branch) {
				String forbiddenId = node.getDialog_node();
				if (forbiddenId == id) isValid = false;
				forbiddenIds.add(forbiddenId);
			}
		}
		// If supplied id is valid returns it
		if (isValid)
			return id;
		// Else try to generate some id
		else {
			String genId = "node_" + forbiddenIds.size() + "_" + String.valueOf(System.currentTimeMillis());
			while (isValid == false) {
				isValid = true;
				for (String fId : forbiddenIds) {
					if (fId == genId)
						isValid = false; 
				}
				if (!isValid) {
					Thread.sleep(1000);
					genId = "node_" + forbiddenIds.size() + "_" + String.valueOf(System.currentTimeMillis());
				}
			}
			return genId;
		}
	}
	
	// Get node supplying column and line
	public DialogNode get(int column, int line) {
		if (nodesTree.size() <= column) return null;
		else if (nodesTree.get(column).size() <= line) return null;
		else return nodesTree.get(column).get(line);
	}
	
	// Add node supplying column and line
	// Automatically sets parent and previous sibling
	// Accepts if col <= branchs.size()
	// Accepts if line of that col <= branchs.get(col).size()
	// Ignores setted links
	public Dialog add(int column, int line, DialogNode node) {
		// New column, ignores line
		if (column == nodesTree.size()) {
			
		}
			
		return this;
	}
	
	public Dialog add(ArrayList<Integer> path, DialogNode node) { return this;}
	
	public Dialog add(DialogNode node) {return this;}
}
