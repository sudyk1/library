package pl.sudyk.library.app;

import pl.sudyk.library.io.DataReader;
import pl.sudyk.library.model.Book;

public class Library {
    public static void main(String[] args) {
        final String appName = "Biblioteka v0.7";

        Book[] books = new Book[1000];

        DataReader dataReader = new DataReader();
        books[0] = dataReader.readAndCreateBook();
        books[1] = dataReader.readAndCreateBook();
        dataReader.close();

        System.out.println(appName);
        System.out.println("Ksiązki dostępne w bibliotece");
        books[0].printInfo();
        books[1].printInfo();
    }
}
