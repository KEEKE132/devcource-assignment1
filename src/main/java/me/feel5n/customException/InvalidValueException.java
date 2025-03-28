package me.feel5n.customException;

public class InvalidValueException extends Exception {
    public InvalidValueException(String message) {
        super(message);
    }

    public InvalidValueException() {
        super();
    }

    public InvalidValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidValueException(Throwable cause) {
        super(cause);
    }
}
