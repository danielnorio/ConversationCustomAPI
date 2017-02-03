package conversation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import conversation.enums.CommandName;
import okhttp3.Response;

public class CommandExecuted {

	private Response response;
	private String body;
	private String bodyContentType;
	private CommandName commandName;

	
	private ArrayList<Intent> intentsLoaded;
	
	public CommandExecuted(Response response, String body, String bodyContentType, CommandName commandName)
	{
		this.response = response;
		this.body = body;
		this.bodyContentType = bodyContentType;
		this.commandName = commandName;
	}

	public Response getResponse() { return response;}
	public String getBody() { return body;}
	public String getBodyContentType() { return bodyContentType;}	
	public CommandName getCommandName() { return commandName;}
	
	//Helper
	public String safeCast(JSONObject jsonObject, String string) {
		return jsonObject.get(string) == null? null : jsonObject.get(string).toString();
	}
	
	//Métodos para parsear body de retorno
	
	public String getStateId() throws ParseException, IOException
	{
		JSONObject obj = (JSONObject) (new JSONParser()).parse(body);
		return obj.get("state").toString();
	}
	
	public Intent getIntent() throws ParseException
	{
		JSONObject intentJson = (JSONObject) (new JSONParser()).parse(body);
		String name = intentJson.get("intent").toString();
        String description = intentJson.get("description").toString();
        String created = intentJson.get("created").toString();
        String updated = intentJson.get("updated").toString();
        return new Intent(name, description, created, updated);
	}
	
	public ArrayList<Intent> getIntents() throws ParseException
	{
		ArrayList<Intent> intents = new ArrayList<Intent>();
		
		JSONObject obj = (JSONObject) (new JSONParser()).parse(body);

		// loop array
        JSONArray msg = (JSONArray) obj.get("intents");
        @SuppressWarnings("unchecked")
		Iterator<JSONObject> iterator = msg.iterator();
        while (iterator.hasNext()) {
        	JSONObject intentJson = iterator.next();
        	String name = intentJson.get("intent").toString();
        	String description = intentJson.get("description").toString();
        	String created = intentJson.get("created").toString();
        	String updated = intentJson.get("updated").toString();
        	intents.add(new Intent(name, description, created, updated));
        }

		return intents;
	}
	
	public IntentExample getIntentExample() throws ParseException
	{
		JSONObject exampleJson = (JSONObject) (new JSONParser()).parse(body);
    	String text = exampleJson.get("text").toString();
    	String created = exampleJson.get("created").toString();
    	String updated = exampleJson.get("updated").toString();
        return new IntentExample(text, created, updated);		
	}
	
	public ArrayList<IntentExample> getIntentExamples() throws ParseException
	{
		ArrayList<IntentExample> examples = new ArrayList<IntentExample>();
		
		JSONObject obj = (JSONObject) (new JSONParser()).parse(body);
		
		// loop array
        JSONArray examplesJSON = (JSONArray) obj.get("examples");
        if (examplesJSON != null) {
	        @SuppressWarnings("unchecked")
			Iterator<JSONObject> iterator = examplesJSON.iterator();
	        while (iterator.hasNext()) {
	        	JSONObject exampleJson = iterator.next();
	        	String text = exampleJson.get("text").toString();
	        	String created = exampleJson.get("created").toString();
	        	String updated = exampleJson.get("updated").toString();
	            examples.add(new IntentExample(text, created, updated));
	        }
        }
		return examples;
	}
	
	public ArrayList<Intent> getIntentsWithExamples()
	{
		return this.intentsLoaded;
	}


	public void setIntentsLoaded(ArrayList<Intent> intentsLoaded) {
		this.intentsLoaded = intentsLoaded;
	}
	
	public Entity getEntity() throws ParseException
	{
		JSONObject entityJson = (JSONObject) (new JSONParser()).parse(body);
		String name = entityJson.get("entity").toString();
        String created = entityJson.get("created").toString();
        String updated = entityJson.get("updated").toString();
        return new Entity(name, created, updated);
	}

	public ArrayList<Entity> getEntities() throws ParseException
	{
		ArrayList<Entity> entities = new ArrayList<Entity>();
		
		JSONObject obj = (JSONObject) (new JSONParser()).parse(body);

		// loop array
        JSONArray msg = (JSONArray) obj.get("entities");
        @SuppressWarnings("unchecked")
		Iterator<JSONObject> iterator = msg.iterator();
        while (iterator.hasNext()) {
        	JSONObject entityJson = iterator.next();
        	Entity e = new Entity(
                	safeCast(entityJson,"entity"),
                	safeCast(entityJson,"created"),
                	safeCast(entityJson,"updated"),
                	safeCast(entityJson,"type"),
                	safeCast(entityJson,"source"),
                	safeCast(entityJson,"open_list"),
                	safeCast(entityJson,"description"));
        	JSONArray values = (JSONArray) entityJson.get("values");
        	@SuppressWarnings("unchecked")
        	Iterator<JSONObject> iteratorVal = values.iterator();
        	while (iteratorVal.hasNext()) {
        		JSONObject valueJson = iteratorVal.next();
        		EntityValue ev = new EntityValue(
        				safeCast(valueJson,"value"), 
        				safeCast(valueJson,"created"), 
        				safeCast(valueJson,"updated"), 
        				safeCast(valueJson,"metadata"),
        				true);
        		JSONArray synonymsJson = (JSONArray) valueJson.get("synonyms");
        		for (int i = 0; i < synonymsJson.size(); i++) {
        			ev.addSynonym(synonymsJson.get(i).toString());
        		}
            	e.addValue(ev);
        		
        	}
        	entities.add(e);
        }

		return entities;	
	}
	
	public ArrayList<DialogNode> getDialogNodes() throws ParseException
	{
		ArrayList<DialogNode> dialogNodes = new ArrayList<DialogNode>();
		
		JSONObject obj = (JSONObject) (new JSONParser()).parse(body);

		// loop array
        JSONArray msg = (JSONArray) obj.get("dialog_nodes");
        @SuppressWarnings("unchecked")
		Iterator<JSONObject> iterator = msg.iterator();
        while (iterator.hasNext()) {
        	JSONObject dialogJson = iterator.next();
    		dialogNodes.add( new DialogNode (
    			safeCast(dialogJson,"go_to"), 
    			safeCast(dialogJson,"output"),
    			safeCast(dialogJson,"parent"),
    			safeCast(dialogJson,"context"),
    			safeCast(dialogJson,"created"),
    			safeCast(dialogJson,"updated"),
    			safeCast(dialogJson,"metadata"),
    			safeCast(dialogJson,"conditions"),
    			safeCast(dialogJson,"description"),
    			safeCast(dialogJson,"dialog_node"),
    			safeCast(dialogJson,"previous_sibling")
    			));
        }

		return dialogNodes;	
	}
	
	public ResponseMessage getMessage() throws ParseException {
		JSONObject Json = (JSONObject) (new JSONParser()).parse(body);
		String contextString = Json.get("context").toString();
		String responseMsg = Json.get("response").toString();
		return new ResponseMessage(responseMsg, contextString);
	}
}
