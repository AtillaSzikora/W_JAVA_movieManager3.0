package main;

import product.Movie;
import product.Game;
import product.Book;
import java.util.Random;

public class IdGenerator {

    public static String generate(Product product) {

        String preFix = "";
        int randomNum;

        if(product instanceof Movie) {preFix = "MOV";}
        if(product instanceof Game) {preFix = "GAM";}
        if(product instanceof Book) {preFix =  "BOO";}
        Random r = new Random();
        randomNum = r.nextInt(10000 - 1000) + 1000;
        String strRandomNum = String.valueOf(randomNum);
        return preFix + strRandomNum; }
}