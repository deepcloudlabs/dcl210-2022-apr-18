package com.example.reactive;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class BinanceWebSocketClient {
	private static final String BINANCE_WS_API = "wss://stream.binance.com:9443/ws/btcusdt@trade";

	public static void main(String[] args) {
		var listener = new BinanceWebsocketListener();
		HttpClient.newHttpClient()
	               .newWebSocketBuilder()
	               .buildAsync(URI.create(BINANCE_WS_API), listener);
		try {TimeUnit.SECONDS.sleep(60);} catch (InterruptedException e) {}		
	}

}

class BinanceWebsocketListener implements Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Connected to Binance's WebSocket Server.");
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println(data);
		webSocket.request(1);
		return Listener.super.onText(webSocket, data, last);
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Disconnected from Binance's WebSocket Server.");
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("Error has occurred: %s.".formatted(error.getMessage()));
	}
	
}