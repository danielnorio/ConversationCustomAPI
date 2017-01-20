package conversation.commands.intent;

import conversation.Command;
import conversation.ConversationConnection;
import conversation.enums.CommandName;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GetIntentExamples extends Command {

	public GetIntentExamples(OkHttpClient client, CommandName nomeComando, ConversationConnection conversationConnection, String intentName) {
		super(client, nomeComando);
		
		setRequest(new Request.Builder()
				  .url("https://www.ibmwatsonconversation.com/rest/v1/workspaces/"+conversationConnection.getWorkspaceId()+"/intents/"+intentName+"/examples")
				  .get()
				  .addHeader("x-xsrf-token", conversationConnection.getXXSRFToken())
				  .addHeader("cookie", conversationConnection.getCookie())
				  .build());
	}

}
