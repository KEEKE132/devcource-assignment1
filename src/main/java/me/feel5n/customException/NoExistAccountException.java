package me.feel5n.customException;

public class NoExistAccountException extends Exception {
    public NoExistAccountException(String message) {
        super(message);
    }

    public NoExistAccountException() {
        super();
    }

    public NoExistAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoExistAccountException(Throwable cause) {
        super(cause);
    }
}
