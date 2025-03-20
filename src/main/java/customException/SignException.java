package customException;

public class SignException extends Exception {
    public SignException(String message) {
        super(message);
    }

    public SignException() {
        super();
    }

    public SignException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignException(Throwable cause) {
        super(cause);
    }
}
