package pl.sudyk.library.io;

import pl.sudyk.library.model.*;

import java.util.Collection;

public class ConsolePrinter {
    public void printBooks(Collection<Publication> publications) {
        long count = publications.stream()
                .filter(pub -> pub instanceof Book)
                .map(Publication::toString)
                .peek(this::printLine)
                .count();
        if (count == 0) {
            printLine("No books in library.");
        }
    }

    public void printMagazines(Collection<Publication> publications) {
        long count = publications.stream()
                .filter(pub -> pub instanceof Magazine)
                .map(Publication::toString)
                .peek(this::printLine)
                .count();
        if (count == 0) {
            printLine("No magazines in library.");
        }
    }

    public void printUsers(Collection<LibraryUser> users) {
        for (LibraryUser user : users) {
            printLine(user.toString());
        }
    }

    public void printLine(String text) {
        System.out.println(text.toUpperCase());
    }
}
