package main;

import person.Gender;
import product.Book;
import person.Person;
import product.Game;
import product.Genre;
import product.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RentManager {
    
    private static final String FILENAME = "data.ser";

    public static int sumIncome (List<Buyable> someProducts) {
        int totalIncome = 0;
        for(Buyable p : someProducts) {totalIncome += p.getPrice();}
        return totalIncome; }

    public static void writeObject(List<Person> persons) {
        System.out.println("The following objects are Serialized.");
        try { FileOutputStream fos = new FileOutputStream(FILENAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Person p : persons) {
                oos.writeObject(p);
                System.out.println(p);
            }
            oos.close();
        }
        catch (IOException e) {e.printStackTrace();}
    }

    public static void readObject() {
        List<Person> resultObjList = new ArrayList<>();
        System.out.println("\nThe following objects are Deserialized.");
        try {
            FileInputStream fis = new FileInputStream(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {resultObjList.add((Person) ois.readObject());}
                catch (Exception e) {break;}
            }
            ois.close();
        }
        catch (IOException e) {e.printStackTrace();}
        for (Person p : resultObjList) System.out.println(p);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Person jani = new Person("Ja", "Ni", Gender.MALE, 100);
        Person jozsi = new Person("Jo", "Zsi", Gender.MALE, 200);
        Person jolan = new Person("Jo", "Lan", Gender.FEMALE, 300);
        Person jakab = new Person("Ja", "Kab", Gender.MALE, 400);
        Person juci = new Person("Ju", "Ci", Gender.FEMALE, 500);
        Person julcsi = new Person("Jul", "Csi", Gender.FEMALE, 600);

        List<Person> game1Staff = Arrays.asList(jani, jozsi, jolan, jakab);
        List<Person> game2Staff = Arrays.asList(jolan, jakab, juci, julcsi);
        List<Person> game3Staff = Arrays.asList(jozsi, jolan, jakab, juci);
        List<Person> movie1Cast = Arrays.asList(jani, jolan, juci);
        List<Person> movie2Cast = Arrays.asList(jozsi, jakab, julcsi);
        List<Person> movie3Cast = Arrays.asList(jakab, juci, julcsi);

        Book book1 = new Book("Java for N00bs", jani);
        Book book2 = new Book("Java for Pros", jozsi);
        Book book3 = new Book("Android basics", jolan);
        Game game1 = new Game("Rome 2", true, game1Staff, 150);
        Game game2 = new Game("Chess", false, game2Staff, 250);
        Game game3 = new Game("Battlefield 4", true, game3Staff, 350);
        Movie movie1 = new Movie("Suits", Genre.ACTION, 110, 3, movie1Cast, 450);
        Movie movie2 = new Movie("Good Wife", Genre.DRAMA, 120, 4, movie2Cast, 550);
        Movie movie3 = new Movie("Blue Bloods", Genre.COMEDY, 100, 2, movie3Cast, 650);

        List<Buyable> someProducts = Arrays.asList(movie1, movie2, game2, game3);

        writeObject(game1Staff);
        readObject();
    }
}