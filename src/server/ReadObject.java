package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadObject {

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
        return objectList;
    }
}