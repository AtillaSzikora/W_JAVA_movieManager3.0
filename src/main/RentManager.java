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

        int i = 1;
        List<Object> objectList = Arrays.asList(
                Instantiation.instanceMap().get("jani"),
                Instantiation.instanceMap().get("jozsi"),
                Instantiation.instanceMap().get("jolan"));

        Socket socket = new Socket("127.0.0.1", ObjectServer.PORT);
        System.out.println("Client initiated connection...");
        ObjectOutputStream soos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream sois = new ObjectInputStream(socket.getInputStream());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\n\t1. (PUT) send objects to the server" +
                             "\n\t2. (GET) read objects from the server" +
                             "\n\t0. (EXIT) shutdown server and client" +
                             "\n(" + i++ + ") Chsoose a number to send command to the server: ");
            int clientMode = scanner.nextInt();

            if (clientMode == 1) {
                soos.writeObject(Command.PUT);
                soos.writeObject(objectList);
                System.out.println("\n- - - LISTED OBJECTS ARE SENT TO THE SERVER - - -");
                for (Object o : objectList) System.out.println(o); }
            else if (clientMode == 2) {
                soos.writeObject(Command.GET);
                List newObjectList = (List) sois.readObject();
                System.out.println("\n- - - LISTED OBJECTS ARE RECIEVED FROM THE SERVER - - -");
                for (Object o : newObjectList) System.out.println(o); }
            else if (clientMode == 0) {
                soos.writeObject(Command.EXIT);
                System.out.println("\nServer and client shut down."); break; }
            else System.out.println("\tThere is no such command. TRY AGAIN!");}

        scanner.close(); sois.close(); soos.close(); socket.close(); }
}