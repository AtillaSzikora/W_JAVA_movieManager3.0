package main;

import product.Instantiation;
import server.Command;
import server.ObjectServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class RentManager {

    static int clientMode = 2;  // PUT 1 | GET 2 | EXIT 0

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        List<Object> objectList = Arrays.asList(
                Instantiation.instanceMap().get("movie1"),
                Instantiation.instanceMap().get("movie2"),
                Instantiation.instanceMap().get("movie3"));

        Socket socket = new Socket("127.0.0.1", ObjectServer.PORT);
        System.out.println("Client initiated connection...\n");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        switch (clientMode) {
            case 1 : {
                oos.writeObject(Command.PUT);
                oos.writeObject(objectList);
                System.out.println("- - - Listed objects are sent to the server - - -");
                for (Object o : objectList) System.out.println(o); break; }
            case 2 : {
                oos.writeObject(Command.GET);
                List newObjectList = (List) ois.readObject();
                System.out.println("- - - Listed objects are recieved from the server - - -");
                for (Object o : newObjectList) System.out.println(o); break; }
            case 0 : {
                oos.writeObject(Command.EXIT);
                System.out.println("\nServer closed connection."); break; } }

        socket.close();
        System.out.println("\nClient closed connection.");
    }
}