package conversation;

import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import conversation.commands.intent.CreateIntent;
import conversation.commands.intent.CreateIntentExample;
import conversation.commands.intent.DeleteIntent;
import conversation.commands.intent.DeleteIntentExample;
import conversation.commands.intent.GetIntentExamples;
import conversation.commands.intent.GetIntents;
import conversation.commands.intent.UpdateIntent;
import conversation.commands.intent.UpdateIntentExample;
import conversation.enums.CommandName;

public class ConversationAPI extends HttpAPI {

	private ConversationConnection conversationConnection;

	public ConversationAPI(ConversationConnection conversationConnection) {
		this.conversationConnection = conversationConnection;
	}
	
	@Override
	public ConversationAPI setLogInConsole(boolean logInConsole) {
		super.setLogInConsole(logInConsole);
		return this;
	}
	
	////Comandos
	
	// 1. Comandos de Intent
	
	// 1.1. CRUD para Intent
	
	//Create
	public ConversationAPI createIntent(Intent intent)
	{
		CreateIntent i = new CreateIntent(getClient(), CommandName.CreateIntent, conversationConnection, intent);
		saveHistory(i.execute(isLoggingInConsole()));
		return this;
	}
	
	//Retrieve
	public ConversationAPI getIntents()
	{
		GetIntents i = new GetIntents(getClient(), CommandName.GetIntents, conversationConnection);
		saveHistory(i.execute(isLoggingInConsole()));
		return this;
	}
	
	//Update
	public ConversationAPI updateIntent(String intentName, String newIntentName)
	{
		UpdateIntent i = new UpdateIntent(getClient(), CommandName.UpdateIntent, conversationConnection, intentName, newIntentName);
		saveHistory(i.execute(isLoggingInConsole()));
		return this;
	}
	
	//Delete
	public ConversationAPI deleteIntent(String intentName)
	{
		DeleteIntent i = new DeleteIntent(getClient(), CommandName.DeleteIntent, conversationConnection, intentName);
		saveHistory(i.execute(isLoggingInConsole()));
		return this;
	}
	
	//1.2 CRUD de IntentExample
	
	//Create
	public ConversationAPI createIntentExample(String intentName, String exampleName)
	{
		CreateIntentExample i = new CreateIntentExample(getClient(), CommandName.CreateIntentExample, conversationConnection, intentName, exampleName);
		saveHistory(i.execute(isLoggingInConsole()));
		return this;
	}
	
	//Restore
	public ConversationAPI getIntentExamples(String intentName)
	{
		GetIntentExamples i = new GetIntentExamples(getClient(), CommandName.GetIntentExamples, conversationConnection, intentName);
		saveHistory(i.execute(isLoggingInConsole()));
		return this;
	}
	
	//Update
	public ConversationAPI updateIntentExample(String intentName, String exampleName, String newExampleName)
	{
		UpdateIntentExample i = new UpdateIntentExample(getClient(), CommandName.UpdateIntentExample, conversationConnection, intentName, exampleName, newExampleName);
		saveHistory(i.execute(isLoggingInConsole()));
		return this;
	}
	
	//Delete
	public ConversationAPI deleteIntentExample(String intentName, String exampleName)
	{
		DeleteIntentExample i = new DeleteIntentExample(getClient(), CommandName.DeleteIntentExample, conversationConnection, intentName, exampleName);
		saveHistory(i.execute(isLoggingInConsole()));
		return this;
	}
	
	
	//1.3 Extras: Comandos Compostos
	public ConversationAPI deleteAllIntents() throws ParseException
	{
		ArrayList<Intent> intents = getIntents().result().getIntents();
		for (Intent i : intents) deleteIntent(i.getName());
		saveHistory(new CommandExecuted(null, null, null, CommandName.DeleteAllIntents));
		return this;
	}
	
	public ConversationAPI getIntentsWithExamples() throws ParseException
	{
		@SuppressWarnings("unchecked")
		ArrayList<Intent> intents = (ArrayList<Intent>)getIntents().resultObject();
		for (Intent i : intents)
		{
			@SuppressWarnings("unchecked")
			ArrayList<IntentExample> examplesOfI = (ArrayList<IntentExample>) getIntentExamples(i.getName()).resultObject();
			i.setIntentExamples(examplesOfI);
		}
		saveHistory(new CommandExecuted(null, null, null, CommandName.GetIntentsWithExamples));
		result().setIntentsLoaded(intents);
		return this;
	}
	
}
