package pl.sudyk.library.io.file;

import pl.sudyk.library.exception.NoSuchFileTypeException;
import pl.sudyk.library.io.ConsolePrinter;
import pl.sudyk.library.io.DataReader;

public class FileManagerBuilder {
    private ConsolePrinter printer;
    private DataReader dataReader;

    public FileManagerBuilder(ConsolePrinter printer, DataReader dataReader) {
        this.printer = printer;
        this.dataReader = dataReader;
    }

    public FileManager build() {
        printer.printLine("Choose data format:");
        FileType fileType = getFileType();
        switch (fileType) {
            case SERIAL -> {
                return new SerializableFileManager();
            }
            default -> throw new NoSuchFileTypeException("Unsupported data type.");
        }
    }

    private FileType getFileType() {
        boolean typeOk = false;
        FileType result = null;
        do {
            printTypes();
            //serial, SERIAL
            String type = dataReader.getString().toUpperCase();
            try {
                result = FileType.valueOf(type);
                typeOk = true;
            } catch (IllegalArgumentException e) {
                printer.printLine("Unsupported data type, select again.");
            }
        } while (!typeOk);
        return result;
    }

    private void printTypes() {
        for (FileType value : FileType.values()) {
            printer.printLine(value.name());
        }
    }
}
