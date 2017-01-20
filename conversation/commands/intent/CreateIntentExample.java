package conversation.commands.intent;

import org.json.simple.JSONObject;

import conversation.Command;
import conversation.ConversationConnection;
import conversation.enums.CommandName;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CreateIntentExample extends Command {

	@SuppressWarnings("unchecked")
	public CreateIntentExample(OkHttpClient client, CommandName nomeComando, ConversationConnection conversationConnection, String intentName, String exampleText) {
		super(client, nomeComando);

		JSONObject payload = new JSONObject();
        payload.put("text", exampleText);
        
		MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
		RequestBody body = RequestBody.create(mediaType, payload.toJSONString());
		setRequest(new Request.Builder()
		  .url("https://www.ibmwatsonconversation.com/rest/v1/workspaces/"+conversationConnection.getWorkspaceId()+"/intents/"+intentName+"/examples")
		  .post(body)
		  .addHeader("x-xsrf-token", conversationConnection.getXXSRFToken())
		  .addHeader("cookie", conversationConnection.getCookie())
		  .build());
	}

}
