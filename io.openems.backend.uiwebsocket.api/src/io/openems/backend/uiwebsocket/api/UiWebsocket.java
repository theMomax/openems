package io.openems.backend.uiwebsocket.api;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.osgi.annotation.versioning.ProviderType;

import io.openems.common.exceptions.OpenemsError.OpenemsNamedException;
import io.openems.common.jsonrpc.base.JsonrpcNotification;
import io.openems.common.jsonrpc.base.JsonrpcRequest;
import io.openems.common.jsonrpc.base.JsonrpcResponseSuccess;

@ProviderType
public interface UiWebsocket {

	/**
	 * Send a JSON-RPC Request to a UI session via WebSocket and expect a JSON-RPC
	 * Response.
	 * 
	 * @param token   the UI token
	 * @param request the JsonrpcRequest
	 * @return the JSON-RPC Success Response Future
	 * @throws OpenemsNamedException on error
	 */
	public CompletableFuture<JsonrpcResponseSuccess> send(UUID token, JsonrpcRequest request)
			throws OpenemsNamedException;

	/**
	 * Send a JSON-RPC Notification to a UI session.
	 * 
	 * @param token        the UI token
	 * @param notification the JsonrpcNotification
	 * @throws OpenemsNamedException on error
	 */
	public void send(UUID token, JsonrpcNotification notification) throws OpenemsNamedException;

}
