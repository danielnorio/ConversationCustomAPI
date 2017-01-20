package conversation.commands.intent;

import conversation.Command;
import conversation.ConversationConnection;
import conversation.enums.CommandName;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DeleteIntentExample extends Command {

	public DeleteIntentExample(OkHttpClient client, CommandName nomeComando, ConversationConnection conversationConnection, String intentName, String exampleName) {
		super(client, nomeComando);

		setRequest(new Request.Builder()
				  .url("https://www.ibmwatsonconversation.com/rest/v1/workspaces/"+conversationConnection.getWorkspaceId()+"/intents/"+intentName+"/examples/"+exampleName)
				  .delete()
				  .addHeader("x-xsrf-token", conversationConnection.getXXSRFToken())
				  .addHeader("cookie", conversationConnection.getCookie())
				  .build());
	}
}
