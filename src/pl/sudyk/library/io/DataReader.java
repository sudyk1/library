package pl.sudyk.library.io;

import pl.sudyk.library.model.Book;

import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);

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

    public int getInt() {
        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }

    public void close() {
        sc.close();
    }
}
