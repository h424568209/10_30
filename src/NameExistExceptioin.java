public class NameExistExceptioin extends Exception {
    public NameExistExceptioin() {
    }

    public NameExistExceptioin(String message) {
        super(message);
    }

    public NameExistExceptioin(String message, Throwable cause) {
        super(message, cause);
    }

    public NameExistExceptioin(Throwable cause) {
        super(cause);
    }

    public NameExistExceptioin(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
