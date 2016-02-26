package server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialization {

    public static void serialize(List<Object> objectList, String fileName) {
        System.out.println("- - - LISTED OBJECTS ARE SERIALIZED TO '" + fileName + "' - - -");
        try {
            AppendOOS faoos = new AppendOOS(new FileOutputStream(fileName, true));
            for (Object o : objectList) {faoos.writeObject(o); System.out.println(o);}
            faoos.close(); }
        catch (IOException e) {e.printStackTrace();} }

    public static List<Object> deserialize(String fileName) {
        System.out.println("- - - LISTED OBJECTS ARE DESERIALIZED FROM '" +fileName + "' - - -");
        List<Object> objectList = new ArrayList<>();
        Object tempObj;
        try {
            ObjectInputStream fois = new ObjectInputStream(new FileInputStream(fileName));
            int i = 0;
            while (true) {
                try {
                    tempObj = fois.readObject();
                    if (tempObj != Command.HEADER) {
                        objectList.add(tempObj);
                    System.out.println(objectList.get(i++)); } }
                catch (IOException e) {break;} }
            fois.close(); }
        catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
        return objectList; }
}