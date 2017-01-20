package conversation;

import conversation.commands.auth.AuthSvcGetStateId;
import conversation.commands.auth.AuthSvcGetToken;
import conversation.commands.auth.IdentifySources;
import conversation.enums.CommandName;

public class AuthAPI extends HttpAPI {
	
	//Comandos
	public AuthAPI identifySources(String user)
	{
		IdentifySources i = new IdentifySources(getClient(), CommandName.IdentifySources, user);
		saveHistory(i.execute(isLoggingInConsole()));
		return this;
	}
	
	public AuthAPI authSvcGetState()
	{
		AuthSvcGetStateId a = new AuthSvcGetStateId(getClient(), CommandName.AuthSvcGetStateId);
		saveHistory(a.execute(isLoggingInConsole()));
		return this;
	}
	
	public AuthAPI authSvcGetToken(String stateId, String email, String senha)
	{
		AuthSvcGetToken i = new AuthSvcGetToken(getClient(), CommandName.AuthSvcGetToken, stateId, email, senha);
		saveHistory(i.execute(isLoggingInConsole()));
		return this;
	}
}
