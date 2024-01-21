package it.library;

import it.library.classes.Book;
import it.library.classes.LibraryArchive;
import it.library.classes.Magazine;
import it.library.enums.Periodicity;
import it.library.superClass.LibraryItem;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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
                str.append(((Book) item).getAuthor()).append("@").append(((Book) item).getGenre());
            }
            writeFile.append(item.getIsbnCode()).append("@").append(item.getTitle()).append("@").append(item.getPublicationYear()).append("@").append(item.getNumPages()).append("@").append(str).append("#");
        }
        for (LibraryItem item : archive.getMagazineArchive()) {
            StringBuilder str = new StringBuilder();
            if (item instanceof Magazine) {
                str.append(((Magazine) item).getPeriodicity()).append("@");
            }
            writeFile.append(item.getIsbnCode()).append("@").append(item.getTitle()).append("@").append(item.getPublicationYear()).append("@").append(item.getNumPages()).append("@").append(str).append("#");
        }
        File file = new File("src/main/java/it/library/file/archive.txt");
        FileUtils.writeStringToFile(file, writeFile.toString(), "UTF-8");
    }

    private static String readFile(String filePath) throws IOException{
        LibraryArchive archive = new LibraryArchive();
        String file = FileUtils.readFileToString(new File("src/main/java/it/library/file/archive.txt"), "UTF-8");
        String[] archiveFile = file.split("#");
        System.out.println(Arrays.toString(archiveFile));
        for(int i =0; i<archiveFile.length; i++){
            String[] singleItem = archiveFile[i].split("@");
            if(singleItem.length > 5){
                Book book = new Book(singleItem[1], Integer.parseInt(singleItem[2]), Integer.parseInt(singleItem[3]), singleItem[4], singleItem[5]);
                book.setIsbnCode(Integer.parseInt(singleItem[0]));
                archive.addBook(book);
            } else{
                Magazine magazine = new Magazine(singleItem[1], Integer.parseInt(singleItem[2]), Integer.parseInt(singleItem[3]), getEnum(singleItem[4]));
                magazine.setIsbnCode(Integer.parseInt(singleItem[0]));
                archive.addMagazine(magazine);
            }
        }
        System.out.println(archive);
        return file;
    }

    public static Periodicity getEnum(String param){
        switch (param) {
            case "WEEKLY":
                return Periodicity.WEEKLY;
            case "MONTHLY":
                return Periodicity.MONTHLY;
            case "HALFYEARLY":
                return Periodicity.HALFYEARLY;
        }
        return null;
    }
}
