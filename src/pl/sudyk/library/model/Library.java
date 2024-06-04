package pl.sudyk.library.model;

public class Library {
    private final int maxBooks = 1000;
    private Book[] books = new Book[maxBooks];
    private int booksNumber = 0;

    public void addBook(Book book) {
        if (booksNumber < maxBooks) {
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
