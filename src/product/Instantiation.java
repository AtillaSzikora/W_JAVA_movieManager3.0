package product;

import person.Gender;
import person.Person;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Instantiation {

    public static Map<String, Object> instanceMap() {

        Person jani = new Person("Ja", "Ni", Gender.MALE, 100);
        Person jozsi = new Person("Jo", "Zsi", Gender.MALE, 200);
        Person jolan = new Person("Jo", "Lan", Gender.FEMALE, 300);
        Person jakab = new Person("Ja", "Kab", Gender.MALE, 400);
        Person juci = new Person("Ju", "Ci", Gender.FEMALE, 500);
        Person julcsi = new Person("Jul", "Csi", Gender.FEMALE, 600);
        Person jeno = new Person("Je", "No", Gender.MALE, 700);
        Person janka = new Person("Jan", "Ka", Gender.FEMALE, 800);
        Person jazmin = new Person("Jaz", "Min", Gender.FEMALE, 900);

        List<Person> g1Staff = Arrays.asList(jani, jozsi, jolan);
        List<Person> g2Staff = Arrays.asList(jakab, juci, julcsi);
        List<Person> g3Staff = Arrays.asList(jeno, janka, jazmin);
        List<Person> m1Cast = Arrays.asList(jani, jakab, jeno);
        List<Person> m2Cast = Arrays.asList(jozsi, juci, janka);
        List<Person> m3Cast = Arrays.asList(jolan, julcsi, jazmin);

        Book book1 = new Book("Java for N00bs", jani);
        Book book2 = new Book("Java for Pros", jozsi);
        Book book3 = new Book("Android basics", jolan);
        Game game1 = new Game("Rome 2", true, g1Staff, 150);
        Game game2 = new Game("WoW", false, g2Staff, 250);
        Game game3 = new Game("Battlefield 4", true, g3Staff, 350);
        Movie movie1 = new Movie("Suits", Genre.ACTION, 110, 3, m1Cast, 450);
        Movie movie2 = new Movie("Good Wife", Genre.DRAMA, 120, 4, m2Cast, 550);
        Movie movie3 = new Movie("Blue Bloods", Genre.COMEDY, 100, 2, m3Cast, 650);

        return new HashMap<String, Object>() {{
            put("jani", jani); put("jozsi", jozsi); put("jolan", jolan);
            put("jakab", jakab); put("juci", juci); put("julcsi", julcsi);
            put("jeno", jeno); put("janka", janka); put("jazmin", jazmin);
            put("g1Staff", g1Staff); put("g2Staff", g2Staff); put("g3Staff", g3Staff);
            put("m1Cast", m1Cast); put("m2Cast", m2Cast); put("m3Cast", m3Cast);
            put("book1", book1); put("book2", book2); put("book3", book3);
            put("game1", game1); put("game2", game2); put("game3", game3);
            put("movie1", movie1); put("movie2", movie2); put("movie3", movie3);
        }}; }
}