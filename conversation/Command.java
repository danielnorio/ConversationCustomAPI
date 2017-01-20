package conversation;

import java.io.IOException;

import conversation.enums.CommandName;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Command {

	private OkHttpClient client;
	private Request request;
	private CommandName nomeComando;
	
	private boolean consoleMode = false;
	
	private boolean alreadyExecuted = false;
	
	private String executionLog = "";
	
	
	private static final String baseLog = "[CustomConversationAPI]";
	
	public void setRequest(Request request) {
		this.request = request;
	}

	public boolean isConsoleMode() {
		return consoleMode;
	}

	public void setConsoleMode(boolean isConsoleMode) {
		this.consoleMode = isConsoleMode;
	}

	public Command(OkHttpClient client, CommandName nomeComando)
	{
		this.client = client;
		this.nomeComando = nomeComando;
		
		//Seta request
	};
	
	public CommandExecuted execute(boolean isConsoleMode)
	{
		this.consoleMode = isConsoleMode;
		return execute();
	}
	
	public CommandExecuted execute()
	{
		if (alreadyExecuted)
		{
			return null;
		}
		else
		{	
			String body;
			String bodyContentType;
			
			try {
				
				executionLog += "\n\n\n";
				imprime("Request do comando "+nomeComando);
				imprime("Url: " + request.url().toString());
				imprime("Método HTTP: " + request.method()+"\n");
				
				
				Response response = client.newCall(request).execute();
				
				imprime("Response do comando "+nomeComando);
				imprime("Código: "+response.code());
				imprime("Mensagem de Status: "+response.message()+"\n");
				imprime("Headers: "+response.headers().toString());
				
				bodyContentType = response.body().contentType().toString();
				body = response.body().string();
				
				imprime("Content-Type do Body: "+bodyContentType);
				imprime("Body: "+body);
			
				alreadyExecuted = true;
				if (consoleMode) System.out.println(executionLog);
				return new CommandExecuted(response, body, bodyContentType, nomeComando);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
	
	private void imprime (String msg)
	{
		executionLog += baseLog + " " + msg + "\n";
	}
	
}
