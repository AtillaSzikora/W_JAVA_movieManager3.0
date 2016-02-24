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
import java.util.Scanner;

public class RentManager {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        List<Object> objectList = Arrays.asList(
                Instantiation.instanceMap().get("movie1"),
                Instantiation.instanceMap().get("movie2"),
                Instantiation.instanceMap().get("game2"));

        Socket socket = new Socket("127.0.0.1", ObjectServer.PORT);
        System.out.println("Client initiated connection...");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\n1. (PUT) send objects to the server" +
                             "\n2. (GET) read objects from the server" +
                             "\n0. (EXIT) shutdown server and client" +
                             "\nChoose a number, to send command to the server: ");
            int clientMode = scanner.nextInt();

            if (clientMode == 1) {
                oos.writeObject(Command.PUT);
                oos.writeObject(objectList);
                System.out.println("\n- - - LISTED OBJECTS ARE SENT TO THE SERVER - - -");
                for (Object o : objectList) System.out.println(o); }
            if (clientMode == 2) {
                oos.writeObject(Command.GET);
                List newObjectList = (List) ois.readObject();
                System.out.println("\n- - - LISTED OBJECTS ARE RECIEVED FROM THE SERVER - - -");
                for (Object o : newObjectList) System.out.println(o); }
            if (clientMode == 0) {
                oos.writeObject(Command.EXIT);
                System.out.println("\nServer and client shut down."); break; } }

        socket.close(); }
}