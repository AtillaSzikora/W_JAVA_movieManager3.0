package server;

import product.Instantiation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ObjectServer {

    ServerMode mode;
    Object object;

    Object load() {return object;}
    void save() {}

    public static void main (String[] args) throws IOException, InterruptedException {

        int number, temp;

        ServerSocket serverS = new ServerSocket(1234);              // create server socket
        Socket socket = serverS.accept();                           // accept incoming request to this socket
        Scanner scanner = new Scanner(socket.getInputStream());     // accepts data from client
        number = scanner.nextInt();                                 // store accepted data in a variable

        temp = number * 2;                                          // doubled the number
        PrintStream p = new PrintStream(socket.getOutputStream());  // created object to pass back to the client
        p.println(temp);                                            // pass back the doubled value to the client

//        File serFile = new File("data.ser");
//        List<Object> someObjects = Arrays.asList(
//                Instantiation.instanceMap().get("book1"),
//                Instantiation.instanceMap().get("book2"),
//                Instantiation.instanceMap().get("book2"));
//        Serialization.writeObj(someObjects, serFile);
//        Serialization.readObj(serFile);
    }
}