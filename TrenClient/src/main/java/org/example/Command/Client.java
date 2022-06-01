package org.example.Command;

import java.io.*;
import java.net.Socket;

public class Client{

    public static Object requestHandler(String request) throws IOException,ClassNotFoundException {
        Socket socket = new Socket("localhost", 6666);
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(request);
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object o = objectInputStream.readObject();
        socket.close();
        return o;
    }
}