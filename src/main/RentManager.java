package main;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class RentManager {

    public static void main(String[] args) throws IOException {

        int number, temp;

        Scanner scanner = new Scanner(System.in);                   // accept input from user
        Socket socket = new Socket("127.0.0.1", 1234);              // create socket
        Scanner scanner1 = new Scanner(socket.getInputStream());    // to accept result from server
        System.out.println("Enter any number: ");                   // accept number from user
        number = scanner.nextInt();                                 // save it to a variable

        PrintStream p = new PrintStream(socket.getOutputStream());  // pass number to server
        p.println(number);                                          // print the number onto the server

        temp = scanner1.nextInt();                                  // accept result from server and store
        System.out.println(temp);                                   // print out result got from server
    }
}