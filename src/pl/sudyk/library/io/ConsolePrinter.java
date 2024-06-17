package pl.sudyk.library.io;

import pl.sudyk.library.model.Book;
import pl.sudyk.library.model.Magazine;
import pl.sudyk.library.model.Publication;

public class ConsolePrinter {
    public void printBooks(Publication[] publications) {
        int countBooks = 0;
        for (Publication publication : publications) {
            if (publication instanceof Book) {
                printLine(publication.toString());;
                countBooks++;
            }
        }
        if (countBooks == 0) {
            printLine("No books in library.");
        }
    }

    public void printMagazines(Publication[] publications) {
        int countMagazines = 0;
        for (Publication publication : publications) {
            if (publication instanceof Magazine) {
                printLine(publication.toString());;
                countMagazines++;
            }
        }
        if (countMagazines == 0) {
            printLine("No magazines in library.");
        }
    }

    public void printLine(String text) {
        System.out.println(text.toUpperCase());
    }
}
