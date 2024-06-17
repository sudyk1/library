package pl.sudyk.library.io.file;

import pl.sudyk.library.exception.DataExportException;
import pl.sudyk.library.exception.DataImportException;
import pl.sudyk.library.model.Library;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "Library.o";
    @Override
    public Library importData() {
        try (
                FileInputStream fis= new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            return (Library) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException(FILE_NAME + " not found");
        } catch (IOException e) {
            throw new DataImportException("Faild to read file " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Incompatible data type in " + FILE_NAME);
        }

    }

    @Override
    public void exportData(Library library) {
        try (
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException(FILE_NAME + " not found");
        } catch (IOException e) {
            throw new DataExportException("Failed to save file " + FILE_NAME);
        }

    }
}
