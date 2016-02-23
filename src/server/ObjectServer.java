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

    static void save() {Serialization.serialize(objectList, FILENAME);}
    static List<Object> load() {return Serialization.deserialize(FILENAME);}

    public static void main (String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is waiting for connection...");
        Socket socket = serverSocket.accept();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        Object command = ois.readObject();

        if (command.equals(Command.PUT)) {
            objectList = (List<Object>) ois.readObject();
            System.out.println("\nLISTED OBJECTS ARE RECIEVED FROM THE CLIENT.");
            save(); }
        if (command.equals(Command.GET)) {
            oos.writeObject(load());
            System.out.println("LISTED OBJECTS ARE SENT TO THE CLIENT.");}

        socket.close();
        System.out.println("\nServer closed connection.");
    }
}