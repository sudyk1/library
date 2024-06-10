package pl.sudyk.library.io;

import pl.sudyk.library.model.Book;
import pl.sudyk.library.model.Magazine;

import java.util.Scanner;

public class DataReader {
    private final Scanner sc = new Scanner(System.in);

    public Book readAndCreateBook() {
        System.out.println("Title:");
        String title = sc.nextLine();
        System.out.println("Author: ");
        String author = sc.nextLine();
        System.out.println("Publisher: ");
        String publisher = sc.nextLine();
        System.out.println("ISNB: ");
        String isbn = sc.nextLine();
        System.out.println("Release date: ");
        int releaseDate = getInt();
        System.out.println("Pages: ");
        int pages = getInt();
        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    public Magazine readAndCreateMagazine() {
        System.out.println("Title:");
        String title = sc.nextLine();
        System.out.println("Publisher: ");
        String publisher = sc.nextLine();
        System.out.println("Language: ");
        String language = sc.nextLine();
        System.out.println("Release year: ");
        int year = getInt();
        System.out.println("Release month: ");
        int month = getInt();
        System.out.println("Release day: ");
        int day = getInt();
        return new Magazine(title, publisher, language, year, month, day);
    }

    public int getInt() {
        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }

    public void close() {
        sc.close();
    }
}
