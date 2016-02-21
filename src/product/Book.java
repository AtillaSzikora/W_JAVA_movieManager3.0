package product;

import main.IdGenerator;
import person.Person;
import main.Product;
import java.io.Serializable;

public class Book extends Product implements Serializable{

    private Person author;

    public Book(String title, Person author) {
        this.title = title;
        this.author = author;
        this.id = IdGenerator.generate(this); }

    public Person getAuthor() {return author;}
    public void setAuthor(Person author) {this.author = author;}

    @Override public long getInvestment() {
        int salaryOfAuthor = 0;
        return author.getSalary(); }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + this.id +
                ", title=" + title +
                ", author=" + author.getFirstName() + author.getLastName() +
                ", investment=" + this.getInvestment() +
                '}'; }
}