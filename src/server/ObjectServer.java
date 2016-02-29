package server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ObjectServer {

    static final String FILENAME = "data.bin";
    static Path filePath = Paths.get(FILENAME);
    public static final int PORT = 4567;
    static List objectList;
    static int i = 1;

    static void save() {Serialization.serialize(objectList, FILENAME);}
    static List load() {return objectList = Serialization.deserialize(FILENAME);}

    static void createEmptyFile()throws IOException {
        ObjectOutputStream foos = new ObjectOutputStream(new FileOutputStream(FILENAME));
        foos.close(); }

    public static void main (String[] args) throws IOException {

        if (!Files.isRegularFile(filePath)) {createEmptyFile();}
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
                else if (command.equals(Command.GET)) {
                    soos.writeObject(load());
                    System.out.println("- - - LISTED OBJECTS ARE SENT TO THE CLIENT - - -");
                    for (Object o : objectList) {System.out.println(o);} }
                else if (command.equals(Command.DEL)) {
                    createEmptyFile();
                    System.out.println("- - - ALL OBJECTS ARE DELETED FROM FILE - - -"); }
                else if (command.equals(Command.EXIT)) {
                    System.out.println("Server closed connection.");
                    break; } }
            catch (IOException | ClassNotFoundException e) {e.printStackTrace(); break;} }

        soos.close(); sois.close(); socket.close(); serverSocket.close(); }
}