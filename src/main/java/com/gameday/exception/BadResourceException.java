package com.gameday.exception;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class BadResourceException.
 */
public class BadResourceException extends Exception {

    /** The error messages. */
    private List<String> errorMessages = new ArrayList<>();
            
    /**
     * Instantiates a new bad resource exception.
     */
    public BadResourceException() {
    }

    /**
     * Instantiates a new bad resource exception.
     *
     * @param msg the msg
     */
    public BadResourceException(String msg) {
        super(msg);
    }
    
    /**
     * Gets the error messages.
     *
     * @return the errorMessages
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * Sets the error messages.
     *
     * @param errorMessages the errorMessages to set
     */
    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * Adds the error message.
     *
     * @param message the message
     */
    public void addErrorMessage(String message) {
        this.errorMessages.add(message);
    }
}
