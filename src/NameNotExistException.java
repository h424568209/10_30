public class NameNotExistException extends Exception{
    public NameNotExistException() {
    }

    public NameNotExistException(String message) {
        super(message);
    }

    public NameNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public NameNotExistException(Throwable cause) {
        super(cause);
    }

    public NameNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
