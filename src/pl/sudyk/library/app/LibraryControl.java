package pl.sudyk.library.app;

import pl.sudyk.library.exception.*;
import pl.sudyk.library.io.ConsolePrinter;
import pl.sudyk.library.io.DataReader;
import pl.sudyk.library.io.file.FileManager;
import pl.sudyk.library.io.file.FileManagerBuilder;
import pl.sudyk.library.model.*;
import pl.sudyk.library.model.comparator.AlphabeticalTitleComparator;

import java.util.Comparator;
import java.util.InputMismatchException;

class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private final DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;
    private Library library;

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();

        try {
            library = fileManager.importData();
            printer.printLine("Data has been imported from file.");
        } catch (DataImportException | InvalidDataException e) {
            printer.printLine(e.getMessage());
            printer.printLine("A new base has been initiated.");
            library = new Library();
        }

    }

    void controlLoop() {
        Option option;

        do {
            printOptions();
            option = getOption();
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
                case DELETE_BOOK:
                    deleteBook();
                    break;
                case DELETE_MAGAZINE:
                    deleteMagazine();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case PRINT_USERS:
                    printUsers();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("No such option, choose again.");
            }
        } while (option != Option.EXIT);
    }

    private void printUsers() {
        printer.printUsers(library.getSortedUsers(new Comparator<LibraryUser>() {
            @Override
            public int compare(LibraryUser user1, LibraryUser user2) {
                return user1.getLastName().compareToIgnoreCase(user2.getLastName());
            }
        }));
    }

    private void addUser() {
        LibraryUser libraryUser = dataReader.createLibraryUser();
        try {
            library.addUser(libraryUser);
        } catch (UserAlreadyExistsException e) {
            printer.printLine(e.getMessage());
        }
    }


    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage());
            } catch (InputMismatchException e) {
                printer.printLine("Entered value is not a number. Enter again.");
            }
        }
        return option;
    }

    private void printMagazines() {
        printer.printMagazines(library.getSortedPublications(new AlphabeticalTitleComparator()));
    }

//    private Publication[] getSortedPublications() {
//        Publication[] publications = library.getPublications();
//        Arrays.sort(publications, new AlphabeticalComparator());
//        return publications;
//    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Faild to create magazine, incorrect data.");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void deleteMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            if (library.removePublication(magazine)) {
                printer.printLine("Magazine has been removed from library.");
            } else {
                printer.printLine("There is no such magazine in library.");
            }
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create magazine, incorrect data.");
        }
    }

    private void exit() {
        try {
            fileManager.exportData(library);
            printer.printLine("Data export to file succeeded");
        } catch (DataExportException e) {
            printer.printLine(e.getMessage());
        }
        printer.printLine("Shooting down, bye.");
        dataReader.close();
    }

    private void printBooks() {
        printer.printBooks(library.getSortedPublications(new AlphabeticalTitleComparator()));
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addPublication(book);
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create book, incorrect data.");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void deleteBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            if (library.removePublication(book)) {
                printer.printLine("Book has been removed from library.");
            } else {
                printer.printLine("There is no such book in library.");
            }
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create book, incorrect data.");
        }
    }

    private void printOptions() {
        printer.printLine("Choose option:");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private enum Option {
        EXIT(0, "exit application"),
        ADD_BOOK(1, "add book"),
        ADD_MAGAZINE(2, "add magazine"),
        PRINT_BOOKS(3, "view books"),
        PRINT_MAGAZINES(4, "view magazines"),
        DELETE_BOOK(5, "delete book"),
        DELETE_MAGAZINE(6, "delete magazine"),
        ADD_USER(7, "add user"),
        PRINT_USERS(8, "print users");

        private final int value;
        private final String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("No option of id - " + option);
            }
        }
    }
}
