package it.library.classes;

import it.library.enums.Periodicity;
import it.library.superClass.LibraryItem;

public class Magazine extends LibraryItem {
    private Periodicity periodicity;

    public Magazine(String isbnCode, String title, int publicationYear, int numPages, Periodicity periodicity) {
        super(isbnCode, title, publicationYear, numPages);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "isbn='" + getIsbnCode() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", year='" + getPublicationYear() + '\'' +
                ", pages='" + getNumPages() + '\'' +
                ", periodicity=" + periodicity +
                '}' + "\n";
    }
}
