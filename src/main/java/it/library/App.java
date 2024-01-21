package it.library;

import it.library.classes.Book;
import it.library.classes.LibraryArchive;
import it.library.classes.Magazine;
import it.library.enums.Periodicity;
import it.library.superClass.LibraryItem;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) throws IOException {

        LibraryArchive archive = new LibraryArchive();
        archive.createAll(20,20);
        System.out.println(archive);

        //AGGIUNGI - riceve come parametro l'istanza della classe Book o Magazine
        System.out.println("---ADD---");
        Book lotr = new Book("Lord of the Rings", 1950, 500, "Tolkien", "Fantasy");
        archive.addBook(lotr);
        Magazine ciak = new Magazine("Ciak", 2019, 60, Periodicity.MONTHLY);
        archive.addMagazine(ciak);


        //RIMUOVI - riceve come parametro il codice ISBN
        System.out.println("---REMOVE---");
        archive.removeMagazine(1245);
        archive.removeBook(3456);

        //SEARCH
        System.out.println("---SEARCH---");
        archive.searchBookIsbn(4957);
        archive.searchBookByYear(1950);
        archive.searchMagazineIsbn(1023);
        archive.searchMagazineByYear(2001);
        archive.searchByAuthor("Tolkien");


        System.out.println("---SAVE TO DISK---");
        try{
            saveToDisk(archive);
            System.out.println("Archive saved on archive.txt");
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }

        System.out.println("---READ FILE---");
        try{
            String fileArchive = readFile("src/main/java/it/library/file/archive.txt");
            System.out.println(fileArchive);
        } catch (IOException e) {
            System.err.println("Error during file reading: " + e.getMessage());
        }

    }

    public static void saveToDisk(LibraryArchive archive) throws IOException {
        StringBuilder writeFile = new StringBuilder();
        for (LibraryItem item : archive.getBookArchive()) {
            StringBuilder str = new StringBuilder();
            if (item instanceof Book) {
                str.append(((Book) item).getAuthor()).append("@").append(((Book) item).getTitle());
            }
            writeFile.append(item.getIsbnCode()).append("@").append(item.getTitle()).append("@").append(item.getPublicationYear()).append("@").append(item.getNumPages()).append("@").append(str).append("\n");
        }
        for (LibraryItem item : archive.getMagazineArchive()) {
            StringBuilder str = new StringBuilder();
            if (item instanceof Magazine) {
                str.append(((Magazine) item).getPeriodicity()).append("@").append(((Magazine) item).getTitle());
            }
            writeFile.append(item.getIsbnCode()).append("@").append(item.getTitle()).append("@").append(item.getPublicationYear()).append("@").append(item.getNumPages()).append("@").append(str).append("\n");
        }
        File file = new File("src/main/java/it/library/file/archive.txt");
        FileUtils.writeStringToFile(file, writeFile.toString(), "UTF-8");
    }

    private static String readFile(String filePath) throws IOException{
        return FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
    }
}
