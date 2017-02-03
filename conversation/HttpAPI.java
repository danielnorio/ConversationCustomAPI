package conversation;

import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import okhttp3.OkHttpClient;

public abstract class HttpAPI {
	
	private OkHttpClient client;
	private ArrayList<CommandExecuted> commandsHistory;
	private boolean logInConsole = false;
	
	public boolean isLoggingInConsole() {
		return logInConsole;
	}

	public HttpAPI setLogInConsole(boolean logInConsole) {
		this.logInConsole = logInConsole;
		return this;
	}

	public ArrayList<CommandExecuted> getCommandsHistory() {
		return commandsHistory;
	}

	public HttpAPI() {
		client = new OkHttpClient();
		commandsHistory = new ArrayList<CommandExecuted>();
	};
	
	// Return last command executed
	public CommandExecuted result()
	{
		return commandsHistory.get(commandsHistory.size()-1);
	}
	
	// Return object
	public Object resultObject() throws ParseException
	{
		Object obj;
		switch (result().getCommandName()) {
			case CreateIntent:
				obj = result().getIntent();
				break;
			case GetIntents:
				obj = result().getIntents();
				break;
			case UpdateIntent:
				obj = result().getIntent();
				break;
			case DeleteIntent:
				obj = null;
				break;
			case CreateIntentExample:
				obj = result().getIntentExample();
				break;
			case GetIntentExamples:
				obj = result().getIntentExamples();
				break;
			case UpdateIntentExample:
				obj = result().getIntentExample();
				break;
			case DeleteIntentExample:
				obj = null;
				break;
			case GetIntentsWithExamples:
				obj = result().getIntentsWithExamples();
				break;
			case DeleteAllIntents:
				obj = null;
				break;
			case CreateEntity:
				obj = result().getEntity();
				break;
			case GetEntities:
				obj = result().getEntities();
				break;
			case DeleteEntity:
				obj = null;
				break;
			case DeleteAllEntities:
				obj = null;
				break;
			case GetDialogNodes:
				obj = result().getDialogNodes();
				break;
			case DeleteDialogNode:
				obj = null;
				break;
			case SendPublicMessage:
				obj = result().getMessage();
				break;
			default:
				obj = result().getDialogNodes();
				break;
		}
		return obj;
	}
	
	public void saveHistory(CommandExecuted commandExecuted)
	{
		commandsHistory.add(commandExecuted);
	}

	public OkHttpClient getClient() {
		return client;
	}
}
