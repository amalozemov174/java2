package com.example.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ClientController controller;

    private String nick;
    public ChatClient(ClientController controller){
        this.controller = controller;
    }

    public void openConnection() throws IOException{
        socket = new Socket("localhost", 8189);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        new Thread(() ->{
            try{
                waitAuth();
                readMessage();
            }
            finally {
                closeConnection();
            }

        }).start();
    }

    private void waitAuth() {
        while(true){
            try {
                final String msg = in.readUTF();
                if(msg.startsWith("/authok")){
                    final String[] messages = msg.split(" ");
                    nick = messages[1];
                    controller.addMessage("Successful authorization by nickname " + nick);
                    controller.toggleBoxesVisibility(true);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void readMessage() {
        while(true){
            try {
                final String msg = in.readUTF();
                if("/end".equals(msg)){
                    controller.toggleBoxesVisibility(false);
                    break;
                }
                controller.addMessage(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void closeConnection() {
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
                socket.close();
            }
        }
        catch (IOException e){
            throw  new RuntimeException("Error of closing", e);
        }
    }


    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick(){
        return this.nick;
    }
}
