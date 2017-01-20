package temp;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import conversation.ConversationAPI;
import conversation.ConversationConnection;
import conversation.Intent;
import conversation.IntentExample;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		ConversationConnection cc = new ConversationConnection("Ponha o workspace id aqui",
				"Ponha o cookie aqui",
				"Ponha o XSRF Token aqui");
		
		ConversationAPI api = new ConversationAPI(cc);
		
		Intent i = new Intent("testeeee");
		i.addExample("ex1");
		i.addExample("ex2");
		
		@SuppressWarnings({ "unused", "unchecked" })
		ArrayList<IntentExample> intents = (ArrayList<IntentExample>) api
			.setLogInConsole(true)
			.createIntent(i)
			.createIntentExample("testeeee", "ex3")
			.getIntentsWithExamples()
			.resultObject()
			;
		
		
		
	}

}
