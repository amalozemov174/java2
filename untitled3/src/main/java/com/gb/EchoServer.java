package com.gb;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    DataOutputStream out;
    DataInputStream in;
    Socket socket;

    public static void main(String[] args){
        EchoServer echoServer = new EchoServer();
        echoServer.initServer();
        echoServer.getMessages();
        echoServer.sendMessages();
    }

    private void sendMessages() {
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while (true){
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        String s = br.readLine();
                        if(s.equalsIgnoreCase("end")){
                            //break;
                            System.out.println("Сервер завершает работу");
                            out.writeUTF(s);
                            System.exit(0);
                        }
                        out.writeUTF(s);
                        System.out.println("Сообщение отправлено клиенту: " + s);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        th2.start();
    }

    private void initServer() {
        try(ServerSocket socketSrv = new ServerSocket(9911)){
            System.out.println("Сервер запущен ожидаем подключение от клиента...");
            socket = socketSrv.accept();
            System.out.println("Клиент подключился...");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getMessages() {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        String s = in.readUTF();
                        if(s.equalsIgnoreCase("end")){
                            System.out.println("Сервер завершает работу");
                            System.exit(0);
                        }

                        System.out.println("Получено сообщение от клиента:" + s);
                        out.writeUTF("Echo# " + s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
    }


}

