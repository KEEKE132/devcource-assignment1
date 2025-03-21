package customException;

public class NotAllowedAuthorityException extends Exception {
    public NotAllowedAuthorityException(String message) {
        super(message);
    }

    public NotAllowedAuthorityException() {
        super();
    }

    public NotAllowedAuthorityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAllowedAuthorityException(Throwable cause) {
        super(cause);
    }
}
