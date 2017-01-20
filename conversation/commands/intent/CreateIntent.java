package conversation.commands.intent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import conversation.Command;
import conversation.ConversationConnection;
import conversation.Intent;
import conversation.IntentExample;
import conversation.enums.CommandName;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CreateIntent extends Command {

	@SuppressWarnings("unchecked")
	public CreateIntent(OkHttpClient client, CommandName nomeComando, ConversationConnection conversationConnection, Intent intent) {
		super(client, nomeComando);

		
		JSONObject payload = new JSONObject();
        payload.put("intent", intent.getName());
        payload.put("description", intent.getDescription());
        
        JSONArray examples = new JSONArray();
        for (IntentExample example : intent.getExamples()) { 
        	JSONObject text = new JSONObject();
            text.put("text", example.getText());
            examples.add(text);
        }
        
        payload.put("examples", examples);
        System.out.println("JSON gerado: " + payload.toJSONString());
        
		MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
		RequestBody body = RequestBody.create(mediaType, payload.toJSONString());
		setRequest(new Request.Builder()
		  .url("https://www.ibmwatsonconversation.com/rest/v1/workspaces/"+conversationConnection.getWorkspaceId()+"/intents")
		  .post(body)
		  .addHeader("x-xsrf-token", conversationConnection.getXXSRFToken())
		  .addHeader("cookie", conversationConnection.getCookie())
		  .build());
	}

}
