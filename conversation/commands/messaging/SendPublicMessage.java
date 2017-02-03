package conversation.commands.messaging;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import conversation.Command;
import conversation.enums.CommandName;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SendPublicMessage extends Command {

	@SuppressWarnings("unchecked")
	public SendPublicMessage(OkHttpClient client, CommandName nomeComando, String urlBluemix, String input, String context) throws ParseException {
		super(client, nomeComando);
		// TODO Auto-generated constructor stub
		
		JSONObject payload = new JSONObject();
        payload.put("input", input);
        
        if (context != "") {
        	JSONParser parser = new JSONParser();
        	JSONObject json = (JSONObject) parser.parse(context);
        	payload.put("context", json);
        }
        //System.out.println("JSON gerado: " + payload.toJSONString());
        
		MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
		RequestBody body = RequestBody.create(mediaType, payload.toJSONString());
		setRequest(new Request.Builder()
		  .url(urlBluemix)
		  .post(body)
		  .build());

	}

}
