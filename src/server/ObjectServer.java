package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ObjectServer {

    static final String FILENAME = "data.ser";
    public static final int PORT = 4567;
    static List objectList = new ArrayList<>();
    ServerMode mode;
    static int i = 0;

    static void save() {Serialization.serialize(objectList, FILENAME);}
    static List<Object> load() {return objectList = Serialization.deserialize(FILENAME);}

    public static void main (String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket = serverSocket.accept();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        while (true) {
            try {
                System.out.println("\n(" + i++ + ") Server is waiting for connection...\n");
                Object command = ois.readObject();
                if (command.equals(Command.PUT)) {
                    objectList = (List) ois.readObject();
                    System.out.println("- - - LISTED OBJECTS ARE RECIEVED FROM THE CLIENT - - -");
                    for (Object o : objectList) System.out.println(o);
                    save(); }
                if (command.equals(Command.GET)) {
                    oos.writeObject(load());
                    System.out.println("- - - LISTED OBJECTS ARE SENT TO THE CLIENT - - -");
                    for (Object o : objectList) System.out.println(o); }
                if (command.equals(Command.EXIT)) {break;} }
            catch (IOException e) {break;} }

        socket.close();
        System.out.println("Server closed connection.");
    }
}