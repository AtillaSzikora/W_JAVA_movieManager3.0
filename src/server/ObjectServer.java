package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectServer {

    static final String FILENAME = "data.bin";
    static Path filePath = Paths.get(FILENAME);
    public static final int PORT = 4567;
    static List objectList = new ArrayList<>();
    static int i = 1;

    static void save() {Serialization.serialize(objectList, FILENAME);}
    static List<Object> load() {return objectList = Serialization.deserialize(FILENAME);}

    public static void main (String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        if (!Files.isRegularFile(filePath)) {
            ObjectOutputStream foos = new ObjectOutputStream(new FileOutputStream("data.bin"));
            foos.writeObject(i);
            foos.close(); }

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is waiting for connection...");
        Socket socket = serverSocket.accept();
        ObjectInputStream sois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream soos = new ObjectOutputStream(socket.getOutputStream());

        while (true) {
            try {
                Object command = sois.readObject();
                System.out.println("\n(" + i++ + ") " + command + " request arrived from client...");
                if (command.equals(Command.PUT)) {
                    objectList = (List) sois.readObject();
                    System.out.println("- - - LISTED OBJECTS ARE RECIEVED FROM THE CLIENT - - -");
                    for (Object o : objectList) {System.out.println(o);}
                    save(); }
                if (command.equals(Command.GET)) {
                    soos.writeObject(load());
                    System.out.println("- - - LISTED OBJECTS ARE SENT TO THE CLIENT - - -");
                    for (Object o : objectList) {System.out.println(o);} }
                if (command.equals(Command.EXIT)) {break;} }
            catch (IOException e) {e.printStackTrace(); break;} }

        soos.close(); sois.close(); socket.close(); serverSocket.close();
        System.out.println("Server closed connection."); }
}
