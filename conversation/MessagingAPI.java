package conversation;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import conversation.commands.messaging.SendPublicMessage;
import conversation.enums.CommandName;

public class MessagingAPI extends HttpAPI {

	private String url = "";
	
	private String contextString = "";
	
	public MessagingAPI(String url)
	{
		super();
		this.url = url;
	}
	
	@Override
	public MessagingAPI setLogInConsole(boolean logInConsole) {
		super.setLogInConsole(logInConsole);
		return this;
	}
	
	//Send message
	public MessagingAPI sendMessage(String input) throws ParseException
	{
		SendPublicMessage sm = new SendPublicMessage(getClient(), CommandName.SendPublicMessage, this.url, input, contextString);
		CommandExecuted ce = sm.execute(isLoggingInConsole());
		JSONObject entityJson = (JSONObject) (new JSONParser()).parse(ce.getBody());
		contextString = entityJson.get("context").toString();
		saveHistory(ce);
		return this;
	}
}
