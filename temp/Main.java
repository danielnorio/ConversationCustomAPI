package temp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import conversation.CommandExecuted;
import conversation.ConversationAPI;
import conversation.ConversationConnection;
import conversation.Dialog;
import conversation.DialogNode;
import conversation.Entity;
import conversation.EntityValue;
import conversation.Intent;
import conversation.IntentExample;
import conversation.commands.messaging.SendPublicMessage;
import conversation.enums.CommandName;
import okhttp3.OkHttpClient;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		ConversationConnection cc = new ConversationConnection("73ff72ab-df00-4988-8bc4-9666fb29ba0a",
				"_gat=1; XSRF-TOKEN=pDlBUFWA-xQCrgJalp4q7sHOayLPdfFSCMvc; _ga=GA1.2.498335840.1484870287; __VCAP_ID__=4e58e445-c96d-49bb-7cfe-5169df9118b1; JSESSIONID=s%3ARtlnpXs6pxkMVY3NThBLz3M4TQhtekCc.pqNG8vHy%2FPZdm9zOAeWvyYiErGjkqsjZzB8ls1O9QNs",
				"pDlBUFWA-xQCrgJalp4q7sHOayLPdfFSCMvc");
		
		ConversationAPI api = new ConversationAPI(cc);
		
		Entity e = new Entity("teste7");
		EntityValue ev1 = new EntityValue("val1");
		ev1.addSynonym("vall1");
		EntityValue ev2 = new EntityValue("val2");
		ev2.addSynonym("vall2");
		ev2.addSynonym("valll2");
		e.addValue(ev1);
		e.addValue(ev2);
		
		//@SuppressWarnings({ "unchecked" })
		//ArrayList<DialogNode> a = (ArrayList<DialogNode>) api.setLogInConsole(true)
		//	.getDialogNodes().resultObject();
		//Dialog d = new Dialog(a);
		
		//@SuppressWarnings("unused")
		//ArrayList<ArrayList<DialogNode>> arv = d.getArvore();
		String context = "";
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Digite algo: ");
			String input = scan.next();
			SendPublicMessage n = new SendPublicMessage(new OkHttpClient(), CommandName.SendPublicMessage, "https://jogos-bot-br.mybluemix.net/dialog/conversation", input, context);
			CommandExecuted ce= n.execute();
			System.out.println("Headers: " + ce.getResponse());
			System.out.println("Body: " + ce.getBody());
			context = ce.getBodyContext();
			
		}
	}

}
