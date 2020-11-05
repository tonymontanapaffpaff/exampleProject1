package by.gsu.epamlab.exceptions;


public class ConnectException extends RuntimeException {

    public ConnectException() {
    }

    public ConnectException(String reason) {
        super(reason);
    }

    public ConnectException(Throwable cause) {
        super(cause);
    }

    public ConnectException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
