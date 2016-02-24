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

    static int clientMode = 3;  // PUT 1 | GET 2 | EXIT 0

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        List<Object> objectList = Arrays.asList(
                Instantiation.instanceMap().get("movie1"),
                Instantiation.instanceMap().get("movie2"),
                Instantiation.instanceMap().get("movie3"));

        Socket socket = new Socket("127.0.0.1", ObjectServer.PORT);
        System.out.println("Client initiated connection...\n");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Choose a number, to send command to the server:" +
//                         "\n\t1: (PUT) write objects to file" +
//                         "\n\t2: (GET) read objects from file" +
//                         "\n\t3: (EXIT) shutdown server" +
//                         "\n\t0: terminate client");
//        scanner.nextInt();

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
            case 3 : {
                oos.writeObject(Command.EXIT);
                System.out.println("\nClient closed connection.");
                break; } }

        socket.close(); }
}