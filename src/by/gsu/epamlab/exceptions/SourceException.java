package by.gsu.epamlab.exceptions;

import java.io.IOException;

public class SourceException extends IOException {

    public SourceException() {
    }

    public SourceException(String message) {
        super(message);
    }

    public SourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SourceException(Throwable cause) {
        super(cause);
    }
}
