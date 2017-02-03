package conversation.enums;

public enum CommandName {
	
	//Auth
	IdentifySources, AuthSvcGetStateId, AuthSvcGetToken,
	
	//Intent
	CreateIntent, GetIntents, UpdateIntent, DeleteIntent,
	CreateIntentExample, GetIntentExamples, UpdateIntentExample, DeleteIntentExample,
	GetIntentsWithExamples, DeleteAllIntents,
	
	//Entity
	CreateEntity, GetEntities, DeleteEntity, DeleteAllEntities,
	
	//Dialog
	GetDialogNodes, DeleteDialogNode,
	
	//Messaging
	SendPublicMessage;
}
