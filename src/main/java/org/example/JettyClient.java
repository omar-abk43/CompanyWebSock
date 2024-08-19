package org.example;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Scanner;

public class JettyClient extends WebSocketClient {
    public JettyClient(URI link){
        super(link);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected");
        send("uid=154678925");
        send("subscribe=QO.ADCB.ADX");
        userSend();
    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Closed Connection");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public void userSend(){
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a message to send: ");
            String comp = scanner.nextLine();
            send(comp);
            comp="";
        }
    }

    public static void main(String[] args) throws Exception {
    URI ServerURI= URI.create("ws://localhost:8000");
    JettyClient Client= new JettyClient(ServerURI);
    Client.connect();
    }
}
