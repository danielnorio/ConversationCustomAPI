package conversation;

public class ResponseMessage {

	private String message;
	private String contextString;
	
	public ResponseMessage(String message, String contextString) {
		this.message = message;
		this.contextString = contextString;
	}
	
	public String getMessage() { return message;}
	public void setMessage(String message) { this.message = message;}
	public String getContextString() { return contextString;}
	public void setContextString(String contextString) { this.contextString = contextString;}

}
