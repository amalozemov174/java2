package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    public Socket socket;
    public ChatServer chatServer;
    public String nick;
    public final DataInputStream in;
    public final DataOutputStream out;
    public AuthService authService;

    public ClientHandler(Socket socket, ChatServer chatServer, AuthService authService) {
        try{
            this.authService = authService;
            this.socket = socket;
            this.chatServer = chatServer;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try{
                    authenticate();
                    readMessage();
                }
                finally {
                    closeConnection();
                }

            }).start();
        }
        catch (Exception e){
            throw  new RuntimeException("Error creation client connection...", e);
        }

    }

    private void authenticate() {
        while (true){
            try {
                final String message = in.readUTF();
                if(message.startsWith("/auth")){
                    final String[] s = message.split(" ");
                    final String login = s[1];
                    final String password = s[2];
                    final String nick = authService.getNickByLoginAndPassword(login, password);
                    if (nick != null){
                        if (chatServer.isNickBusy(nick)){
                            sendMessage("User has authorized already");
                            continue;
                        }
                        sendMessage("/authok " + nick);
                        this.nick = nick;
                        chatServer.broadcast("User " + nick + " join the chat");
                        chatServer.subscribe(this);
                        break;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readMessage(){
        while (true){
            try {
                final String msg = in.readUTF();
                if("/end".equals(msg)){
                    break;
                }

                if(msg.startsWith("/w")){
                    final String[] args = msg.split(" ");
                    String recipient = args[1];
                    String message = "";
                    for(int i = 2; i < args.length; i++){
                        message = message + " " + args[i];
                    }
                    chatServer.unicast(message.trim(), recipient);
                }
                else {
                    System.out.println("Get message: " + msg);
                    chatServer.broadcast(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void closeConnection(){
        sendMessage("/end");
        try{
            if(in != null){
                in.close();
            }
        }
        catch (IOException e){
            throw  new RuntimeException("Error of closing", e);
        }

        try{
            if(out != null){
                out.close();
            }
        }
        catch (IOException e){
            throw  new RuntimeException("Error of closing", e);
        }

        try{
            if(socket != null){
                chatServer.unsubscrube(this);
                socket.close();
            }
        }
        catch (IOException e){
            throw  new RuntimeException("Error of closing", e);
        }
    }

    public void sendMessage(String message){
        try {
            System.out.println("Sending the message " + message);
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return this.nick;
    }
}
