package me.feel5n.customException;

public class NoExistBoardException extends Exception {
    public NoExistBoardException(String message) {
        super(message);
    }

    public NoExistBoardException() {
        super();
    }

    public NoExistBoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoExistBoardException(Throwable cause) {
        super(cause);
    }
}
