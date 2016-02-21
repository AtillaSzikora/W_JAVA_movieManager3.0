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
        FileOutputStream fos = new FileOutputStream("data.str");
        FileInputStream fis = new FileInputStream("data.str");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        ObjectInputStream ois = new ObjectInputStream(fis);
        while(true) {
            ss.wait();
        }
    }
}
