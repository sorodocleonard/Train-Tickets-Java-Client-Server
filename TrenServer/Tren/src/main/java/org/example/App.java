package org.example;


import org.example.Command.DecodeCmd;
import org.example.Model.Administrator;
import org.example.Res.EmailSender;
import org.example.Res.MsgSender;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class App{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(6666);
        System.out.println("ServerSocket is on..");
        EmailSender e = new EmailSender();


        while (true) {
            Socket socket = ss.accept();
            System.out.println("Connection from " + socket + "!");
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            String request = (String) objectInputStream.readObject();
            DecodeCmd dec = new DecodeCmd();
            Object o = dec.decode(request);

            objectOutputStream.writeObject(o);

        }
    }
}