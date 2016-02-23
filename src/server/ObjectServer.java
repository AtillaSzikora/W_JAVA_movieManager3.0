package server;

import product.Instantiation;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ObjectServer {

    ServerMode mode;
    Object o;
    static final String FILENAME = "data.ser";
    public static final int PORT = 4567;
    static List<Object> objectList;

    Object load() {return o;}
    void save() {}

    public static void main (String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is waiting for connection...");
        Socket socket = serverSocket.accept();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        objectList = (List<Object>)ois.readObject();
        socket.close();
        Serialization.writeObj(objectList, FILENAME);
    }
}