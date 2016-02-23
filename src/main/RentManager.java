package main;

import product.Instantiation;
import server.ObjectServer;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class RentManager {

    private static final String FILENAME = "data.ser";
    private static List<Object> objectList;

    public static void main(String[] args) throws IOException {

        objectList = Arrays.asList(
                Instantiation.instanceMap().get("book1"),
                Instantiation.instanceMap().get("book2"),
                Instantiation.instanceMap().get("book3"));

        Socket socket = new Socket("127.0.0.1", ObjectServer.PORT);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(objectList);
        socket.close();
    }
}