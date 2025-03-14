package customException;

public class InvalidUrlException extends Exception {
    public InvalidUrlException(String message) {
        super(message);
    }

    public InvalidUrlException() {
        super();
    }

    public InvalidUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUrlException(Throwable cause) {
        super(cause);
    }
}
