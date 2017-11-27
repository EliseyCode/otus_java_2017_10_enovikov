package ru.enovikow.otus.exceptions;

public class NoSuchNoteException extends ATMException {
    public NoSuchNoteException(String message) {
        super(message);
    }
}
