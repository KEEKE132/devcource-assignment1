package me.feel5n.customException;

public class NoExistParameterException extends Exception {
    public NoExistParameterException(String message) {
        super(message);
    }

    public NoExistParameterException() {
        super();
    }

    public NoExistParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoExistParameterException(Throwable cause) {
        super(cause);
    }
}
