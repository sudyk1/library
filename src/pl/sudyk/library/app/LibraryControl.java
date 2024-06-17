package pl.sudyk.library.app;

import pl.sudyk.library.exception.DataExportException;
import pl.sudyk.library.exception.DataImportException;
import pl.sudyk.library.exception.NoSuchOptionException;
import pl.sudyk.library.io.ConsolePrinter;
import pl.sudyk.library.io.DataReader;
import pl.sudyk.library.io.file.FileManager;
import pl.sudyk.library.io.file.FileManagerBuilder;
import pl.sudyk.library.model.Book;
import pl.sudyk.library.model.Library;
import pl.sudyk.library.model.Magazine;

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
        } catch (DataImportException e) {
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
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("No such option, choose again.");
            }
        } while (option != Option.EXIT);
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
        printer.printMagazines(library.getPublications());
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addMagazine(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Faild to create magazine, incorrect data.");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine(e.getMessage());
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
        printer.printBooks(library.getPublications());
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addBook(book);
        } catch (InputMismatchException e) {
            printer.printLine("Faild to create book, incorrect data.");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine(e.getMessage());
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
        PRINT_MAGAZINES(4, "view magazines");

        private final int value;
        private final String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
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
