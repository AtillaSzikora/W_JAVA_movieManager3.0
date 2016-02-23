package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ObjectServer {

    ServerMode mode;
    static final String FILENAME = "data.ser";
    public static final int PORT = 4567;
    static List<Object> objectList = new ArrayList<>();

    List<Object> load() {return objectList;}
    static void save() {Serialization.serialize(objectList, FILENAME);
    }

    public static void main (String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is waiting for connection...");
        Socket socket = serverSocket.accept();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        if (ois.readObject() == Command.PUT) {
            objectList = (List<Object>)ois.readObject();
            save(); }


        socket.close();
        System.out.println("\nServer closed connection.");
    }
}