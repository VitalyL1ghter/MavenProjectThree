package ru.example.exeption;

import java.util.InputMismatchException;

public class WrongLoginException extends InputMismatchException {
    public WrongLoginException() {
    }

    public WrongLoginException(String s) {
        super(s);
    }
}
