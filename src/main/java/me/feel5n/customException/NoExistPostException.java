package me.feel5n.customException;

public class NoExistPostException extends Exception {
    public NoExistPostException(String message) {
        super(message);
    }

    public NoExistPostException() {
        super();
    }

    public NoExistPostException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoExistPostException(Throwable cause) {
        super(cause);
    }
}
