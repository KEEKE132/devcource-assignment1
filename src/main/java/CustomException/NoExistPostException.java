package CustomException;

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

    protected NoExistPostException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
