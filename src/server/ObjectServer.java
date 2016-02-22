package server;

import java.io.*;
import java.net.ServerSocket;

public class ObjectServer {

    ServerMode mode;
    Object object;

    Object load() {return object;}
    void save() {}

    public static void main (String[] args) throws IOException, InterruptedException {

        ServerSocket ss = new ServerSocket();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.str"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.str"));
        ss.close();
        oos.close();
        ois.close();
    }
}