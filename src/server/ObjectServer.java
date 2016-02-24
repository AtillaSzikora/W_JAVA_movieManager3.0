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

    static void save() {Serialization.serialize(objectList, FILENAME);}
    static List<Object> load() {return objectList = Serialization.deserialize(FILENAME);}

    public static void main (String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            try {
                System.out.println("\nServer is waiting for connection...\n");
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Object command = ois.readObject();
                if (command.equals(Command.PUT)) {
                    objectList = (List) ois.readObject();
                    System.out.println("- - - Listed objects are recieved from the client - - -");
                    for (Object o : objectList) System.out.println(o);
                    save(); }
                if (command.equals(Command.GET)) {
                    oos.writeObject(load());
                    System.out.println("- - - Listed objects are sent to the client - - -");
                    for (Object o : objectList) System.out.println(o); }
                if (command.equals(Command.EXIT)) {break;} }
            catch (IOException e) {break;} }
//          socket.close();
        System.out.println("\nServer closed connection.");
    }
}