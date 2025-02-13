package exceptions;

import data.BrowserNameData;

public class BrowserNotSupportedException extends RuntimeException {
    public BrowserNotSupportedException(BrowserNameData browserName) {
        super(String.format("Browser %s is not supported", browserName));
    }
}
