package main;

import person.Gender;
import product.Book;
import person.Person;
import product.Game;
import product.Genre;
import product.Movie;
import server.ReadObject;
import server.WriteObject;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class RentManager {

    public static void main(String[] args) {

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

        List<Object> someProducts = Arrays.asList(book1, book2, book3);
        File serializedFile = new File("data.ser");

        WriteObject.writeObj(someProducts, serializedFile);
        ReadObject.readObj(serializedFile);
    }
}