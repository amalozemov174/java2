package com.gb;

import javafx.application.Application;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    DataOutputStream out;
    DataInputStream in;
    Socket socket;

    public static void main(String[] args){
        EchoClient echoClient = new EchoClient();
        echoClient.initClient();
        echoClient.getMessage();
        echoClient.sendMessage();
    }

    public void initClient(){
        try {
            socket = new Socket("localhost", 9911);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void sendMessage(){
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while (true){
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        String s = br.readLine();
                        if(s.equalsIgnoreCase("end")){
                            System.out.println("Клиент завершает работу");
                            out.writeUTF(s);
                            System.exit(0);

                        }
                        out.writeUTF(s);
                        System.out.println("Сообщение отправлено на сервер: " + s);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        th2.start();
    }

    public void getMessage(){
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while (true){
                        String s = in.readUTF();
                        if(s.equalsIgnoreCase("end")){
                            System.out.println("Клиент завершает работу");
                            System.exit(0);
                        }
                        System.out.println("Сообщение от сервера: " + s);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        th.start();

    }
}
