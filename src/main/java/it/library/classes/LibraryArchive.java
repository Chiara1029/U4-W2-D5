package it.library.classes;

import it.library.superClass.LibraryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryArchive {
    private List<LibraryItem> bookArchive;
    private List<LibraryItem> magazineArchive;

    public LibraryArchive() {
        this.bookArchive = bookArchive = new ArrayList<>();
        this.magazineArchive = magazineArchive = new ArrayList<>();
    }

    public void addBook(LibraryItem item){
        bookArchive.add(item);
    }
    public void removeBook(String isbn){
        bookArchive.removeIf(e -> e.getIsbnCode().equals(isbn));
    }
    public void addMagazine(LibraryItem item){
        magazineArchive.add(item);
    }
    public void removeMagazine(String isbn){
        magazineArchive.removeIf(e -> e.getIsbnCode().equals(isbn));
    }

    public Optional<LibraryItem> searchBookIsbn (String isbn){
        return bookArchive.stream().filter(e -> e.getIsbnCode().equals(isbn)).findFirst();
    }
    public Optional<LibraryItem> searchMagazineIsbn (String isbn){
        return magazineArchive.stream().filter(e -> e.getIsbnCode().equals(isbn)).findFirst();
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
