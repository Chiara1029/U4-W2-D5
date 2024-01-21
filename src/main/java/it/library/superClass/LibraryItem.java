package it.library.superClass;

import java.util.Random;

public abstract class LibraryItem {
    private int isbnCode;
    private String title;
    private int publicationYear;
    private int numPages;

    public LibraryItem(String title, int publicationYear, int numPages) {
        Random random = new Random();
        this.isbnCode = random.nextInt(3000 - 2000) + 2000;;
        this.title = title;
        this.publicationYear = publicationYear;
        this.numPages = numPages;
    }

    public int getIsbnCode() {
        return isbnCode;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getNumPages() {
        return numPages;
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "isbnCode=" + isbnCode + '\'' +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear + "\n" +
                ", numPages=" + numPages +
                '}';
    }
}
