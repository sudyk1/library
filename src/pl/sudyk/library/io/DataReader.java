package pl.sudyk.library.io;

import pl.sudyk.library.model.Book;
import pl.sudyk.library.model.LibraryUser;
import pl.sudyk.library.model.Magazine;

import java.util.Scanner;

public class DataReader {
    private final Scanner sc = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public Book readAndCreateBook() {
        printer.printLine("Title:");
        String title = sc.nextLine();
        printer.printLine("Author: ");
        String author = sc.nextLine();
        printer.printLine("Publisher: ");
        String publisher = sc.nextLine();
        printer.printLine("ISNB: ");
        String isbn = sc.nextLine();
        printer.printLine("Release date: ");
        int releaseDate = getInt();
        printer.printLine("Pages: ");
        int pages = getInt();
        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    public Magazine readAndCreateMagazine() {
        printer.printLine("Title:");
        String title = sc.nextLine();
        printer.printLine("Publisher: ");
        String publisher = sc.nextLine();
        printer.printLine("Language: ");
        String language = sc.nextLine();
        printer.printLine("Release year: ");
        int year = getInt();
        printer.printLine("Release month: ");
        int month = getInt();
        printer.printLine("Release day: ");
        int day = getInt();
        return new Magazine(title, publisher, language, year, month, day);
    }

    public LibraryUser createLibraryUser() {
        printer.printLine("Name:");
        String firstName = sc.nextLine();
        printer.printLine("Last name:");
        String lastName = sc.nextLine();
        printer.printLine("PESEL:");
        String pesel = sc.nextLine();
        return new LibraryUser(firstName, lastName, pesel);
    }

    public int getInt() {
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }
    }

    public String getString() {
        return sc.nextLine();
    }

    public void close() {
        sc.close();
    }
}
