package main;

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
        String clientMode;
        List objectList = Arrays.asList(Instantiation.instanceMap().get("book1"),
                                        Instantiation.instanceMap().get("book2"),
                                        Instantiation.instanceMap().get("book3"));

        Socket socket = new Socket("127.0.0.1", ObjectServer.PORT);
        System.out.println("Client initiated connection...");
        ObjectOutputStream soos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream sois = new ObjectInputStream(socket.getInputStream());
        Scanner scanner = new Scanner(System.in);

        label:
        while (true) {
            System.out.print("\n\t1. (PUT) send objects to the server" +
                    "\n\t2. (GET) read objects from the server" +
                    "\n\t3. (DEL) delete all objects from file" +
                    "\n\t0. (EXIT) shutdown server and client" +
                    "\n\tEnter a number to send command to the server: ");
            clientMode = scanner.next();

            switch (clientMode) {
                case "1": System.out.println("(" + i + ") " + Command.PUT + " request is sent to the server...");
                          soos.writeObject(Command.PUT);
                          soos.writeObject(objectList);
                          System.out.println("\n- - - LISTED OBJECTS ARE SENT TO THE SERVER - - -");
                          for (Object o : objectList) {System.out.println(o);} break;
                case "2": System.out.println("(" + i + ") " + Command.GET + " request is sent to the server...");
                          soos.writeObject(Command.GET);
                          List newObjectList = (List) sois.readObject();
                          System.out.println("\n- - - LISTED OBJECTS ARE RECIEVED FROM THE SERVER - - -");
                          for (Object o : newObjectList) {System.out.println(o);} break;
                case "3": System.out.println("(" + i + ") " + Command.DEL + " request is sent to the server...");
                          soos.writeObject(Command.DEL);
                          System.out.println("\n- - - ALL OBJECTS ARE DELETED FROM FILE - - -"); break;
                case "0": System.out.println("(" + i + ") " + Command.EXIT + " request is sent to the server...");
                          soos.writeObject(Command.EXIT);
                          System.out.println("\nServer and client shut down."); break label;
                default:  System.out.println("There is no such command. TRY AGAIN!"); break; }
            i++; }

        scanner.close(); sois.close(); soos.close(); socket.close(); }
}