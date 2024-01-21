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
        System.out.println(item.getTitle() + " has been added in Books.");
    }
    public void removeBook(int isbn){
        bookArchive.removeIf(e -> e.getIsbnCode() == isbn);
        System.out.println("Book removed");
    }
    public void addMagazine(LibraryItem item){
        magazineArchive.add(item);
        System.out.println(item.getTitle() + " has been added in Magazines.");
    }
    public void removeMagazine(int isbn){
        magazineArchive.removeIf(e -> e.getIsbnCode() == isbn);
        System.out.println("Magazine removed");
    }

    public void searchBookIsbn (int isbn){
        Optional<LibraryItem> foundBook = bookArchive.stream().filter(e -> e.getIsbnCode() == isbn).findFirst();
        if(foundBook.isPresent()) {
            System.out.println("Here's: " + foundBook.get());
        } else {
            System.out.println("There are no books with this code.");
        }
    }
    public void searchMagazineIsbn (int isbn){
        Optional<LibraryItem> foundMagazine = magazineArchive.stream().filter(e -> e.getIsbnCode() == isbn).findFirst();
        if(foundMagazine.isPresent()) {
            System.out.println("Here's: " + foundMagazine.get());
        } else {
            System.out.println("There are no magazines with this code.");
        }
    }

    public void searchBookByYear (int publicationYear){
        List<LibraryItem> foundBooks =  bookArchive.stream().filter(e-> e.getPublicationYear() == publicationYear).collect(Collectors.toList());
        if(!foundBooks.isEmpty()){
            System.out.println("Books published in " + publicationYear + ": " + foundBooks );
        }else {
            System.out.println("No books published in " + publicationYear);
        }
    }
    public void searchMagazineByYear (int publicationYear){
        List<LibraryItem> foundMagazines = magazineArchive.stream().filter(e-> e.getPublicationYear() == publicationYear).collect(Collectors.toList());
        if(!foundMagazines.isEmpty()){
            System.out.println("Magazine published in " + publicationYear + ": " + foundMagazines );
        }else {
            System.out.println("No magazine published in " + publicationYear);
        }
    }
    public void searchByAuthor (String author){
        List<LibraryItem> foundBooks = bookArchive.stream()
                .filter(e -> e instanceof Book && ((Book) e).getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
        if (!foundBooks.isEmpty()) {
            System.out.println("Books written by " + author + ": " + foundBooks);
        } else {
            System.out.println("No books written by " + author + ".");
        }
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
