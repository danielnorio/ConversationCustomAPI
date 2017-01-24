package conversation.commands.entity;

import conversation.Command;
import conversation.ConversationConnection;
import conversation.enums.CommandName;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GetEntities extends Command {

	public GetEntities(OkHttpClient client, CommandName nomeComando, ConversationConnection conversationConnection) {
		super(client, nomeComando);

		setRequest(new Request.Builder()
				  .url("https://www.ibmwatsonconversation.com/rest/v1/workspaces/"+conversationConnection.getWorkspaceId()+"/entities")
				  .get()
				  .addHeader("x-xsrf-token", conversationConnection.getXXSRFToken())
				  .addHeader("cookie", conversationConnection.getCookie())
				  .build());
	}

}
