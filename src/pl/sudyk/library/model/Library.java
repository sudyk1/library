package pl.sudyk.library.model;

import pl.sudyk.library.exception.PublicationAlreadyExistException;
import pl.sudyk.library.exception.UserAlreadyExistsException;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {

    private Map<String, Publication> publications = new HashMap<>();
    private Map<String, LibraryUser> users = new HashMap<>();

    public Map<String, Publication> getPublications() {
        return publications;
    }

    public Collection<Publication> getSortedPublications(Comparator<Publication> comparator) {
        List<Publication> list = new ArrayList<>(publications.values());
        list.sort(comparator);
        return list;
    }

    public Map<String, LibraryUser> getUsers() {
        return users;
    }

    public Collection<LibraryUser> getSortedUsers(Comparator<LibraryUser> comparator) {
        ArrayList<LibraryUser> users = new ArrayList<>(this.users.values());
        users.sort(comparator);
        return users;
    }

    public void addPublication(Publication publication) {
        if (publications.containsKey(publication.getTitle())) {
            throw new PublicationAlreadyExistException("Publication " + publication.getTitle() + ", already exist.");
        }
        publications.put(publication.getTitle(), publication);
    }

    public void addUser(LibraryUser user) {
        if (users.containsKey(user.getPesel())) {
            throw new UserAlreadyExistsException("User of PESEL number " + user.getPesel() + " already exists.");
        }
        users.put(user.getPesel(), user);
    }

    public boolean removePublication(Publication publication) {
        if (publications.containsValue(publication)) {
            publications.remove(publication.getTitle());
            return true;
        } else {
            return false;
        }
    }
}
