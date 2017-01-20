package conversation.commands.auth;

import conversation.Command;
import conversation.enums.CommandName;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class AuthSvcGetToken extends Command {

	public AuthSvcGetToken(OkHttpClient client, CommandName nomeComando, String stateId, String email, String senha) {
		super(client, nomeComando);

		RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("login-form-type", "pwd")
                .addFormDataPart("operation", "verify")
                .addFormDataPart("password", senha)
                .addFormDataPart("redirectURL", "")
                .addFormDataPart("username", email)
                .build();
		
		setRequest(new Request.Builder()
		  .url("https://idaas.iam.ibm.com/idaas/mtfim/sps/authsvc?StateId="+stateId)
		  .method("POST", RequestBody.create(null, new byte[0]))
		  .post(requestBody)
		  .addHeader("origin", "https://idaas.iam.ibm.com")
		  .addHeader("content-type", "form-data")
		  .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		  .addHeader("accept-encoding", "gzip, deflate, br")
		  .addHeader("accept-language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4")

		  .build());
	}

}
