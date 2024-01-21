package it.library.classes;

import com.github.javafaker.Faker;
import it.library.superClass.LibraryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static it.library.classes.Magazine.getRandomPeriodicity;

public class LibraryArchive {
    private static List<LibraryItem> bookArchive;
    private List<LibraryItem> magazineArchive;

    public LibraryArchive() {
        this.bookArchive = bookArchive = new ArrayList<>();
        this.magazineArchive = magazineArchive = new ArrayList<>();
    }

    public void addBook(LibraryItem item){
        bookArchive.add(item);
    }
    public void removeBook(int isbn){
        bookArchive.removeIf(e -> e.getIsbnCode() == isbn);
    }
    public void addMagazine(LibraryItem item){
        magazineArchive.add(item);
    }
    public void removeMagazine(int isbn){
        magazineArchive.removeIf(e -> e.getIsbnCode() == isbn);
    }

    public Optional<LibraryItem> searchBookIsbn (int isbn){
        return bookArchive.stream().filter(e -> e.getIsbnCode() == isbn).findFirst();
    }
    public Optional<LibraryItem> searchMagazineIsbn (int isbn){
        return magazineArchive.stream().filter(e -> e.getIsbnCode() == isbn).findFirst();
    }

    public List<LibraryItem> searchBookByYear (int publicationYear){
        return bookArchive.stream().filter(e-> e.getPublicationYear() == publicationYear).collect(Collectors.toList());
    }
    public List<LibraryItem> searchMagazineByYear (int publicationYear){
        return magazineArchive.stream().filter(e-> e.getPublicationYear() == publicationYear).collect(Collectors.toList());
    }
    public List<LibraryItem> searchByAuthor (String author){
        return bookArchive.stream().filter(e -> e instanceof Book && ((Book) e).getAuthor().toLowerCase().equals(author)).collect(Collectors.toList());
    }

    //FAKER
    public static void createBooks(int amount, List<LibraryItem> bookArchive){
        Faker faker = new Faker(Locale.ITALY);
        Supplier<Book> books = () -> new Book (faker.book().title(), faker.number().numberBetween(1800,2024), faker.number().numberBetween(80, 2000), faker.book().author(), faker.book().genre());
        for(int i=0; i < amount; i++){
            bookArchive.add((LibraryItem) books.get());
        }
    }
    public static void createMagazines(int amount, List<LibraryItem> magazineArchive){
        Faker faker = new Faker(Locale.ITALY);
        Supplier<Magazine> magazines = () -> new Magazine (faker.book().title(), faker.number().numberBetween(1994,2024), faker.number().numberBetween(20, 100), getRandomPeriodicity());
        for(int i=0; i < amount; i++){
            magazineArchive.add((LibraryItem) magazines.get());
        }
    }
    public void createAll(int bookAmount, int magazineAmount){
        createBooks(bookAmount, bookArchive);
        createMagazines(magazineAmount, magazineArchive);
    }
    public List<LibraryItem> getBookArchive() {
        return bookArchive;
    }

    public List<LibraryItem> getMagazineArchive() {
        return magazineArchive;
    }

    @Override
    public String toString() {
        return "LibraryCatalogue{" +
                "bookArchive=" + bookArchive + "\n" +
                "magazineArchive=" + magazineArchive +
                '}';
    }
}
