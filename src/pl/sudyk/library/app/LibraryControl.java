package pl.sudyk.library.app;

import pl.sudyk.library.io.DataReader;
import pl.sudyk.library.model.Book;
import pl.sudyk.library.model.Library;

class LibraryControl {

    private static final int EXIT = 0;
    private static final int ADD_BOOK = 1;
    private static final int PRINT_BOOKS = 2;

    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void controlLoop() {
        int option;

        do {
            printOptions();
            option = dataReader.getInt();
            switch (option) {
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("No such option, choose again.");
            }
        } while (option != EXIT);
    }

    private void exit() {
        System.out.println("Shouting down, bye.");
        dataReader.close();
    }

    private void printBooks() {
        library.printBooks();
    }

    private void addBook() {
        Book book = dataReader.readAndCreateBook();
        library.addBook(book);
    }

    private void printOptions() {
        System.out.println("Choose option:");
        System.out.println(EXIT + " - exit application");
        System.out.println(ADD_BOOK + " - add book");
        System.out.println(PRINT_BOOKS + " - view books");
    }
}
