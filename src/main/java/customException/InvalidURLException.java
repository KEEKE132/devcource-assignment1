package customException;

public class InvalidURLException extends Exception {
    public InvalidURLException(String message) {
        super(message);
    }

    public InvalidURLException() {
        super();
    }

    public InvalidURLException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidURLException(Throwable cause) {
        super(cause);
    }

    protected InvalidURLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
