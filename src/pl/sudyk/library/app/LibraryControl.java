package pl.sudyk.library.app;

import pl.sudyk.library.io.DataReader;
import pl.sudyk.library.model.Book;
import pl.sudyk.library.model.Library;
import pl.sudyk.library.model.Magazine;

class LibraryControl {

    private static final int EXIT = 0;
    private static final int ADD_BOOK = 1;
    private static final int ADD_MAGAZINE = 2;
    private static final int PRINT_BOOKS = 3;
    private static final int PRINT_MAGAZINES = 4;

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
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("No such option, choose again.");
            }
        } while (option != EXIT);
    }

    private void printMagazines() {
        library.printMagazines();
    }

    private void addMagazine() {
        Magazine magazine = dataReader.readAndCreateMagazine();
        library.addMagazine(magazine);
    }

    private void exit() {
        System.out.println("Shooting down, bye.");
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
        System.out.println(ADD_MAGAZINE + " - add magazine");
        System.out.println(PRINT_BOOKS + " - view books");
        System.out.println(PRINT_MAGAZINES + " - view magazines");
    }
}
