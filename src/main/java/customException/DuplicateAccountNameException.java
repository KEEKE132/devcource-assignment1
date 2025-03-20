package customException;

public class DuplicateAccountNameException extends Exception {
    public DuplicateAccountNameException(String message) {
        super(message);
    }

    public DuplicateAccountNameException() {
        super();
    }

    public DuplicateAccountNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateAccountNameException(Throwable cause) {
        super(cause);
    }
}
