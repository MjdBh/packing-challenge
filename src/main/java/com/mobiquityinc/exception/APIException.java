package com.mobiquityinc.exception;


/**
 * Raises when something is wrong with the user input.
 */

public class APIException extends RuntimeException {

    /**
     * Constructs an {@link APIException} with the given {@code message} and
     * {@code cause}.
     *
     * @param message The message.
     * @param cause   The real cause of the exception.
     */
    public APIException(String message, Exception cause) {
        super(message, cause);
    }

    /**
     * Constructs an {@link APIException} instance just by passing a simple message.
     *
     * @param message The exception message.
     */
    public APIException(String message) {
        super(message);
    }
}
