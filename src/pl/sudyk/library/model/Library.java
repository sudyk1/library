package pl.sudyk.library.model;

public class Library {
    private static final int MAX_BOOKS = 1000;
    private Book[] books = new Book[MAX_BOOKS];
    private int booksNumber = 0;

    public void addBook(Book book) {
        if (booksNumber < MAX_BOOKS) {
            books[booksNumber] = book;
            booksNumber++;
        } else {
            System.out.println("Maximum number of books in library has been reached.");
        }
    }

    public void printBooks() {
        if (booksNumber == 0) {
            System.out.println("No books in library.");
        }
        for (int i = 0; i < booksNumber; i++) {
            books[i].printInfo();
        }
    }
}
