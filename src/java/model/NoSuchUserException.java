package model;

public class NoSuchUserException extends Exception {
    public NoSuchUserException() {
        super("There is no such user with the given username and password.");
    }
}
