package it.library;

import it.library.classes.Book;
import it.library.classes.LibraryArchive;
import it.library.classes.Magazine;
import it.library.superClass.LibraryItem;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {

        LibraryArchive archive = new LibraryArchive();
        archive.createAll(20,20);
        System.out.println(archive);




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
