package pl.sudyk.library.app;

class LibraryApp {
    public static void main(String[] args) {
        final String appName = "Library v0.8";
        System.out.println(appName);
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.controlLoop();
    }
}
