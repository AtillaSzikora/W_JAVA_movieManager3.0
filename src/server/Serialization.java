package server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialization {

    public static void serialize(List<Object> objectList, String fileName) {
        System.out.println("- - - Listed objects are serialized to '" + fileName + "' - - -");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            for (Object o : objectList) {
                oos.writeObject(o);
                System.out.println(o); }
            oos.close(); }
        catch (IOException e) {e.printStackTrace();} }

    public static List<Object> deserialize(String fileName) {
        System.out.println("- - - Listed objects are deserialized from '" +fileName + "' - - -");
        List<Object> objectList = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            int i = 0;
            while (true) {
                try {
                    objectList.add(ois.readObject());
                    System.out.println(objectList.get(i++)); }
                catch (IOException e) {break;} }
            ois.close(); }
        catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
        return objectList; }
}