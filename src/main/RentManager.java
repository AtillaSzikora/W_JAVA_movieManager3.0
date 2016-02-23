package main;

import product.Instantiation;
import server.Command;
import server.ObjectServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RentManager {

    static int CLIENTMODE = 2;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        List<Object> objectList = Arrays.asList(
                Instantiation.instanceMap().get("book1"),
                Instantiation.instanceMap().get("book2"),
                Instantiation.instanceMap().get("book3"));

        Socket socket = new Socket("127.0.0.1", ObjectServer.PORT);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        switch (CLIENTMODE) {
            case 1 : {
                oos.writeObject(Command.GET);
                List<Object> newObjectList = (List<Object>) ois.readObject();
                System.out.println("THE LISTED OBJECTS ARE RECIEVED FROM THE SERVER");
                for (Object o : newObjectList) System.out.println(o); }
            case 2 : {
                oos.writeObject(Command.PUT);
                oos.writeObject(objectList);
                System.out.println("\nTHE LISTED OBJECTS ARE SENT TO THE SERVER");
                for (Object o : objectList) System.out.println(o); }
            case 3 : {
                oos.writeObject(Command.EXIT);
                System.out.println("\nServer closed connection."); } }

        socket.close();
        System.out.println("Client closed connection.");
    }
}