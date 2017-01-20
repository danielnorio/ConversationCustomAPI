package conversation.commands.intent;

import conversation.Command;
import conversation.ConversationConnection;
import conversation.enums.CommandName;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DeleteIntent extends Command {

	public DeleteIntent(OkHttpClient client, CommandName nomeComando, ConversationConnection conversationConnection, String nomeIntent) {
		super(client, nomeComando);

		setRequest(new Request.Builder()
				  .url("https://www.ibmwatsonconversation.com/rest/v1/workspaces/"+conversationConnection.getWorkspaceId()+"/intents/"+nomeIntent)
				  .delete()
				  .addHeader("x-xsrf-token", conversationConnection.getXXSRFToken())
				  .addHeader("cookie", conversationConnection.getCookie())
				  .build());
	}

}
