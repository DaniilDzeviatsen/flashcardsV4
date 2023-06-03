package com.example.flashcardsv4.exceptions;

public class InputDataException extends RuntimeException {
    public InputDataException() {
        super("Wrong DATA input");
    }
}
