package com.hanekedesign.hdanalytics;

/**
 * Created by nthunem on 2/1/17.
 */

public class TimedEventException extends Exception {

    public TimedEventException() {}

    public TimedEventException(String message) {
        super(message);
    }

    public TimedEventException(Throwable cause) {
        super(cause);
    }

    public TimedEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
