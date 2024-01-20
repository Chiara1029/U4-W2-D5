package it.library.superClass;

public abstract class LibraryItem {
    private String isbnCode;
    private String title;
    private int publicationYear;
    private int numPages;

    public LibraryItem(String isbnCode, String title, int publicationYear, int numPages) {
        this.isbnCode = isbnCode;
        this.title = title;
        this.publicationYear = publicationYear;
        this.numPages = numPages;
    }

    public String getIsbnCode() {
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
