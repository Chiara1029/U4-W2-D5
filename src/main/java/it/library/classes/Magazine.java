package it.library.classes;

import it.library.enums.Periodicity;
import it.library.superClass.LibraryItem;

import java.util.Random;

public class Magazine extends LibraryItem {
    private Periodicity periodicity;

    public Magazine(String title, int publicationYear, int numPages, Periodicity periodicity) {
        super(title, publicationYear, numPages);
        this.periodicity = periodicity;
    }
    static Periodicity getRandomPeriodicity(){
        Periodicity[] options = Periodicity.values();
        int indexRand = new Random().nextInt(options.length);
        return options[indexRand];
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
