package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private final List<ClientHandler> clients;
    private AuthService authService;

    public ChatServer() {
        this.clients = new ArrayList<>();
        authService = new InMemoryAuthService();
        authService.start();

    }

    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(8189);) {
            while(true){
                System.out.println("server waiting connection from client...");
                final Socket socket = serverSocket.accept();
                System.out.println("Client connected...");
                new ClientHandler(socket, this, authService);
            }
        } catch (IOException e) {
            throw new RuntimeException("Server error", e);
        }
    }

    public boolean isNickBusy(String nick) {
        for(ClientHandler client : clients){
            if(client.getNick().equals(nick)){
                return true;
            }
        }
        return false;
    }

    public void broadcast(String message) {
        for(ClientHandler client : clients){
            client.sendMessage(message);
        }
    }

    public void unicast(String message, String nick){
        for(ClientHandler client : clients){
            if(client.getNick().equals(nick))
                client.sendMessage(message);
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscrube(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

}
