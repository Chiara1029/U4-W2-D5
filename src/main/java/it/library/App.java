package it.library;

import it.library.classes.Book;
import it.library.classes.LibraryArchive;
import it.library.classes.Magazine;
import it.library.enums.Periodicity;
import it.library.superClass.LibraryItem;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Book fahrenheit451 = new Book("9788804487715", "Fahrenheit 451", 1989, 195, "Ray Bradbury", "Dystopian");
        Book hamlet = new Book("9788804314660", "Hamlet", 1988, 343, "William Shakespeare", "Play");
        Book theHelp = new Book("9788804628637", "The Help", 2013, 532, "Kathryn Stockett", "Historical Fiction");
        Book stardust = new Book("9788804547105", "Stardust", 2005, 245, "Neil Gaiman", "Fantasy");
        Book daVinciCode = new Book("9788804523413", "Il Codice Da Vinci", 2003, 528, "Dan Brown", "Thriller");
        Book petitPrince = new Book("9788804648826", "Il Piccolo Principe", 1943, 95, "Antoine de Saint-Exupéry", "Children Literature");
        Book silmarillion = new Book("9788845272400", "Il Silmarillion", 2013, 682, "J.R.R. Tolkien", "Fantasy");
        Book littleWomen = new Book("9788804719892", "Piccole Donne", 2019, 948, "Louisa May Alcott", "Fiction");
        Book americanGods = new Book("9788804520832 ", "American Gods", 2003, 532, "Neil Gaiman", "Urban Fantasy");
        Book theHobbit = new Book("9788845299209", "Lo Hobbit", 2018, 291, "J.R.R. Tolkien", "Fantasy");

        Magazine bestMovie = new Magazine("1824-2588","Best Movie", 2016, 50, Periodicity.MONTHLY);
        Magazine ciak = new Magazine("1121-1784","Ciak", 2019, 45, Periodicity.MONTHLY);
        Magazine pkmnUniv = new Magazine("13568990","Pokémon Universe", 2023, 32, Periodicity.WEEKLY);
        Magazine express = new Magazine("3293743883","Express", 1998, 60, Periodicity.MONTHLY);

        LibraryArchive archive = new LibraryArchive();
        archive.addBook(fahrenheit451);
        archive.addBook(hamlet);
        archive.addBook(theHelp);
        archive.addBook(stardust);
        archive.addBook(daVinciCode);
        archive.addBook(petitPrince);
        archive.addBook(silmarillion);
        archive.addBook(littleWomen);
        archive.addBook(americanGods);
        archive.addBook(theHobbit);

        archive.addMagazine(bestMovie);
        archive.addMagazine(ciak);
        archive.addMagazine(pkmnUniv);
        archive.addMagazine(express);
//    archive.removeBook("9788804314660");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Search by ISBN code: ");
        String isbnInput = scanner.nextLine();
        if(isbnInput.length() == 13) {
            Optional<LibraryItem> item = archive.searchBookIsbn(isbnInput.trim());
            item.ifPresent(e -> System.out.println("Element found: " + e.getTitle()));
        } else {
            System.out.println("Invalid ISBN, try again.");
        }


        System.out.print("Search by author: ");
        String authorInput = scanner.nextLine();
        List<LibraryItem> searchAuth = archive.searchByAuthor(authorInput.trim().toLowerCase());
        System.out.println("Written by this author: ");
        searchAuth.forEach(e -> System.out.println(e.getTitle()));

        System.out.print("Search by publication year: ");
        int yearInput = scanner.nextInt();
        List<LibraryItem> itemsByYear = archive.searchBookByYear(yearInput);
        System.out.println("Published in the same year: ");
        itemsByYear.forEach(e -> System.out.println(e.getTitle()));

        scanner.close();


        try{
            saveToDisk(archive);
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
    }

    public static void saveToDisk(LibraryArchive archive) throws IOException {
        StringBuilder writeFile = new StringBuilder();
        for (LibraryItem item : archive.getBookArchive()) {
            StringBuilder str = new StringBuilder();
            if (item instanceof Book) {
                str.append(((Book) item).getAuthor()).append("@").append(((Book) item).getTitle());
            }
            writeFile.append(item.getIsbnCode()).append("@").append(item.getTitle()).append("@").append(item.getPublicationYear()).append("@").append(item.getNumPages()).append("@").append(str).append("#");
        }for (LibraryItem item : archive.getMagazineArchive()) {
            StringBuilder str = new StringBuilder();
            if (item instanceof Magazine) {
                str.append(((Magazine) item).getPeriodicity()).append("@").append(((Magazine) item).getTitle());
            }
            writeFile.append(item.getIsbnCode()).append("@").append(item.getTitle()).append("@").append(item.getPublicationYear()).append("@").append(item.getNumPages()).append("@").append(str).append("#");
        }
        File file = new File("src/main/java/it/library/file/archive.txt");
        FileUtils.writeStringToFile(file, writeFile.toString(), "UTF-8");
    }
}
