package customException;

public class DuplicateBoardNameException extends Exception {
    public DuplicateBoardNameException(String message) {
        super(message);
    }

    public DuplicateBoardNameException() {
        super();
    }

    public DuplicateBoardNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateBoardNameException(Throwable cause) {
        super(cause);
    }
}
