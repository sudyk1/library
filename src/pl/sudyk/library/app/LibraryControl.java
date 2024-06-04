package pl.sudyk.library.app;

import pl.sudyk.library.io.DataReader;
import pl.sudyk.library.model.Book;
import pl.sudyk.library.model.Library;

class LibraryControl {

    private final int exit = 0;
    private final int addBook = 1;
    private final int printBooks = 2;
    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void controlLoop() {
        int option;

        do {
            printOptions();
            option = dataReader.getInt();
            switch (option) {
                case addBook:
                    addBook();
                    break;
                case printBooks:
                    printBooks();
                    break;
                case exit:
                    exit();
                    break;
                default:
                    System.out.println("No such option, choose again.");
            }
        } while (option != exit);
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
        System.out.println(exit + " - exit application");
        System.out.println(addBook + " - add book");
        System.out.println(printBooks + " - view books");
    }
}
