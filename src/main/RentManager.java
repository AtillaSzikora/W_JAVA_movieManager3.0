package main;

import product.*;
import server.ReadObject;
import server.WriteObject;
import java.io.File;
import java.util.*;

public class RentManager {

    public static void main(String[] args) {

        List<Object> someObjects = Arrays.asList(
                Instantiation.instanceMap().get("book1"),
                Instantiation.instanceMap().get("book2"),
                Instantiation.instanceMap().get("book3"));
        File serializedFile = new File("data.ser");

        WriteObject.writeObj(someObjects, serializedFile);
        ReadObject.readObj(serializedFile);
    }
}