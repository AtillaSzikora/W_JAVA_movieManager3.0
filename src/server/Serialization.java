package server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialization {

    public static void writeObj(List<Object> objects, File file) {
        System.out.println("\nTHE LISTED OBJECTS ARE SERIALIZED TO '" + file.getName() + "'");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            for (Object o : objects) {
                oos.writeObject(o);
                System.out.println(o);
            }
            oos.close();
        }
        catch (IOException e) {e.printStackTrace();} }

    public static List<Object> readObj(File file) {
        System.out.println("\nTHE LISTED OBJECTS ARE DESERIALIZED FROM '" + file.getName() + "'");
        List<Object> objectList = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            int i = 0;
            while (true) {
                try {
                    objectList.add(ois.readObject());
                    System.out.println(objectList.get(i++));
                }
                catch (IOException e) {break;}
            }
            ois.close();
        }
        catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
        return objectList; }
}