package conversation;

public class ConversationConnection {
	
	private String workspaceId;
	private String cookie;
	private String XXSRFToken;
	
	public String getWorkspaceId() { return workspaceId;}
	public String getCookie() { return cookie;}

	public String getXXSRFToken() { return XXSRFToken;}

	public ConversationConnection(String workspaceId, String cookie, String XXSRFToken)
	{
		this.workspaceId = workspaceId;
		this.cookie = cookie;
		this.XXSRFToken = XXSRFToken;
	}
}
