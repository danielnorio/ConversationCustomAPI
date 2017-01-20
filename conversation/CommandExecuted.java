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
}
