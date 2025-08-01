package ru.example.exeption;

import java.util.InputMismatchException;

public class WrongPasswordException extends InputMismatchException {
    public WrongPasswordException() {
    }

    public WrongPasswordException(String s) {
        super(s);
    }
}
