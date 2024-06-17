package pl.sudyk.library.io.file;

import pl.sudyk.library.model.Library;

public interface FileManager {
    Library importData();
    void exportData(Library library);
}
