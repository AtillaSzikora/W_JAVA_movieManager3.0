package server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class WriteObject {

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
        catch (IOException e) {e.printStackTrace();}
    }
}