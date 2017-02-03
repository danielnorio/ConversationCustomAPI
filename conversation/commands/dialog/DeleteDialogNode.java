package conversation.commands.dialog;

import conversation.Command;
import conversation.ConversationConnection;
import conversation.enums.CommandName;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DeleteDialogNode extends Command {

	public DeleteDialogNode(OkHttpClient client, CommandName nomeComando, ConversationConnection conversationConnection, String dialogNode) {
		super(client, nomeComando);

		
		setRequest(new Request.Builder()
				  .url("https://www.ibmwatsonconversation.com/rest/v1/workspaces/"+conversationConnection.getWorkspaceId()+"/dialog_nodes/"+dialogNode)
				  .delete()
				  .addHeader("x-xsrf-token", conversationConnection.getXXSRFToken())
				  .addHeader("cookie", conversationConnection.getCookie())
				  .build());
	}

}
