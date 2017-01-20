package conversation.commands.auth;

import conversation.Command;
import conversation.enums.CommandName;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AuthSvcGetStateId extends Command{

	public AuthSvcGetStateId(OkHttpClient client, CommandName nomeComando) {
		super(client, nomeComando);
		
		setRequest(new Request.Builder()
				//Falta melhorar
				//Alternar as politicas diversas pipeando o policyId obtido do IdentifySources
				  .url("https://idaas.iam.ibm.com/idaas/mtfim/sps/apiauthsvc?PolicyId=urn%3Aibm%3Asecurity%3Aauthentication%3Aasf%3Abasicldapuser")
				  .get()
				  .addHeader("accept", "application/json")
				  .build());
	}
	
	

}
