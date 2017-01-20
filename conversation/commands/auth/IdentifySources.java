package conversation.commands.auth;

import conversation.Command;
import conversation.enums.CommandName;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class IdentifySources extends Command {

	public IdentifySources(OkHttpClient client, CommandName nomeComando, String user) {
		super(client, nomeComando);
		setRequest(new Request.Builder()
				  .url("https://idaas.iam.ibm.com/v1/mgmt/idaas/user/identitysources?user="+user+"&oidcUrl=true")
				  .get()
				  .addHeader("accept-language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4")
				  .addHeader("cache-control", "no-cache")
				  .build());
	}

}
