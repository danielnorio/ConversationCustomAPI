package conversation.commands.entity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import conversation.Command;
import conversation.ConversationConnection;
import conversation.Entity;
import conversation.EntityValue;
import conversation.enums.CommandName;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CreateEntity extends Command {

	@SuppressWarnings("unchecked")
	public CreateEntity(OkHttpClient client, CommandName nomeComando, ConversationConnection conversationConnection, Entity entity) {
		super(client, nomeComando);
		
		JSONObject payload = new JSONObject();
        payload.put("entity", entity.getEntityName());
        
        JSONArray entValues = new JSONArray();
        for (EntityValue val : entity.getValues()) { 
        	JSONObject value = new JSONObject();
            value.put("value", val.getValue());
            JSONArray synonyms = new JSONArray();
            for (String synonym : val.getSynonyms()) { synonyms.add(synonym); }
            value.put("synonyms", synonyms);
            entValues.add(value);
        }
        
        payload.put("values", entValues);
        System.out.println("JSON gerado: " + payload.toJSONString());
        
		MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
		RequestBody body = RequestBody.create(mediaType, payload.toJSONString());
		setRequest(new Request.Builder()
		  .url("https://www.ibmwatsonconversation.com/rest/v1/workspaces/"+conversationConnection.getWorkspaceId()+"/entities")
		  .post(body)
		  .addHeader("x-xsrf-token", conversationConnection.getXXSRFToken())
		  .addHeader("cookie", conversationConnection.getCookie())
		  .build());

	}

}
